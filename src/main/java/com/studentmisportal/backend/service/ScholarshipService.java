package com.studentmisportal.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.studentmisportal.backend.dto.ScholarshipRequestDto;
import com.studentmisportal.backend.dto.ScholarshipResponseDto;
import com.studentmisportal.backend.entity.Notification;
import com.studentmisportal.backend.entity.Scholarship;
import com.studentmisportal.backend.repository.NotificationRepository;
import com.studentmisportal.backend.repository.ScholarshipRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScholarshipService {

    private final Cloudinary cloudinary;
    private final ScholarshipRepository scholarshipRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;

    public List<ScholarshipResponseDto> getAllScholarships(){
        List<Scholarship> scholarships = scholarshipRepository.findAll();

        return scholarships.stream().map(scholarship ->
                modelMapper.map(scholarship, ScholarshipResponseDto.class)).toList();
    }


    public String uploadFile(ScholarshipRequestDto scholarshipDetails) throws IOException {
        MultipartFile file = scholarshipDetails.getFile();

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("resource_type", "raw",
                        "folder", "scholarships")
        );


        String fileUrl = uploadResult.get("secure_url").toString();
        System.out.println("filename" + fileUrl);

        Scholarship scholarship = new Scholarship();
        scholarship.setTitle(scholarshipDetails.getTitle());
        scholarship.setDescription(scholarshipDetails.getDescription());
        scholarship.setFileUrl(fileUrl);
        scholarship.setDeadline(scholarshipDetails.getDeadline());
        scholarship.setCreationDate(LocalDate.now());

        scholarshipRepository.save(scholarship);

//        // 🔔 SEND WEBSOCKET NOTIFICATION
//        messagingTemplate.convertAndSend(
//                "/topic/notifications",
//                "New scholarship uploaded: " + scholarship.getTitle()
//        );

        Notification notification = new Notification();
        notification.setMessage("New scholarship uploaded: " + scholarship.getTitle());
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);

        // send websocket notification
        messagingTemplate.convertAndSend(
                "/topic/notifications",
                notification
        );

        return fileUrl;
    }
}
