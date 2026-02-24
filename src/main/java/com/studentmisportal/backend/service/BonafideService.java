package com.studentmisportal.backend.service;

import com.studentmisportal.backend.dto.BonafideRequestDto;
import com.studentmisportal.backend.dto.BonafideResponseDto;
import com.studentmisportal.backend.entity.Bonafide;
import com.studentmisportal.backend.entity.type.BonafideStatusType;
import com.studentmisportal.backend.repository.BonafideRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BonafideService {

    private final BonafideRepository bonafideRepository;
    private final ModelMapper modelMapper;

    public List<BonafideResponseDto> getAllBonafides(){
        List<Bonafide> allBonafides = bonafideRepository.findAll();

        return allBonafides.stream()
                .map(bonafide-> modelMapper.map(bonafide, BonafideResponseDto.class))
                .toList();
    }

    public List<BonafideResponseDto> getBonafides(String mis){
        List<Bonafide> allBonafides = bonafideRepository.findByStudentMis(mis);

        return allBonafides.stream()
                .map(bonafide-> modelMapper.map(bonafide, BonafideResponseDto.class))
                .toList();
    }

    public String createBonafideRequest(BonafideRequestDto bonafiderequest, String mis, String name) {

        Bonafide bonafide = new Bonafide();
        bonafide.setStudentMis(mis);
        bonafide.setStudentName(name);
        bonafide.setReason(bonafiderequest.getReason());
        bonafide.setRequestedDate(LocalDate.now());
        bonafide.setStatus(BonafideStatusType.PENDING);
        bonafide.setApprovedBy(null);
        bonafide.setApprovedDate(null);

        bonafideRepository.save(bonafide);

        return "Bonafide Created";
    }

    public String approveBonafide(Long id, String facultyName) {
        Bonafide bonafide = bonafideRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        bonafide.setStatus(BonafideStatusType.APPROVED);
        bonafide.setApprovedDate(LocalDate.now());
        bonafide.setApprovedBy(facultyName);
        bonafideRepository.save(bonafide);
        return "Bonafide Approved";
    }

    public String rejectBonafide(Long id, String facultyName) {
        Bonafide bonafide = bonafideRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        bonafide.setStatus(BonafideStatusType.REJECTED);
        bonafide.setRequestedDate(LocalDate.now());
        bonafide.setRejectedBy(facultyName);
        bonafideRepository.save(bonafide);
        return "Bonafide Rejected";
    }
}
