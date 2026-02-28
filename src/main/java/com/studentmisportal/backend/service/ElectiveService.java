package com.studentmisportal.backend.service;

import com.studentmisportal.backend.dto.CreateElectiveRequestDto;
import com.studentmisportal.backend.dto.CreateElectiveResponseDto;
import com.studentmisportal.backend.entity.Elective;
import com.studentmisportal.backend.entity.ElectiveSubject;
import com.studentmisportal.backend.repository.ElectiveRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectiveService {

    private final ElectiveRepository electiveRepository;
    private final ModelMapper modelMapper;

    public List<CreateElectiveResponseDto> findAllElectives() {
        List<Elective> electives = electiveRepository.findAll();
        return electives.stream().map(elective->
                modelMapper.map(elective, CreateElectiveResponseDto.class)).toList();
    }

    public CreateElectiveResponseDto createElective(CreateElectiveRequestDto request, String userName) {

        Elective elective = new Elective();
        elective.setModuleName(request.getModuleName());
        elective.setRegistrationDeadline(request.getRegistrationDeadline());
        elective.setIsPublished(false);
        elective.setCreatedDate(LocalDate.now());
        elective.setCreatedBy(userName);

        List<ElectiveSubject> subjects = request.getSubjects().stream()
                .map(subjectRequest -> {
                    ElectiveSubject subject = new ElectiveSubject();
                    subject.setSubjectName(subjectRequest.getSubjectName());
                    subject.setMaxLimit(subjectRequest.getMaxLimit());
                    subject.setElective(elective);
                    return subject;
                })
                .toList();

        elective.setElectiveSubjects(subjects);

        Elective newElective =  electiveRepository.save(elective);
        return modelMapper.map(newElective,  CreateElectiveResponseDto.class);
    }
}
