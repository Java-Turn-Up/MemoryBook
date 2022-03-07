package com.example.memorybook.service;

import com.example.memorybook.model.entity.Club;
import com.example.memorybook.model.req.ReqFormatClub;
import com.example.memorybook.model.res.ResFormatClub;
import com.example.memorybook.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public ResFormatClub.ResFormatBasicClubInfo getClubInfo(final String _cname){
        Club _club = clubRepository.findByTitle(_cname)
                .orElseThrow(() -> new RuntimeException("There is no " + _cname + " exists"));

        return ResFormatClub.ResFormatBasicClubInfo.builder()
                .club_name(_club.getTitle())
                .club_info(_club.getInfo())
                .build();
    }

    public List<ResFormatClub.ResFormatBasicClubInfo> getAllClub(){
        return clubRepository.findAll().stream()
                .map(e -> (ResFormatClub.ResFormatBasicClubInfo.builder()
                        .club_name(e.getTitle())
                        .club_info(e.getInfo())
                        .build()
                ))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Void> postClub(final ReqFormatClub.BasicClubInfo req){
        clubRepository.save(
                Club.builder()
                .title(req.getClub_title())
                .info(req.getClub_info())
                .build()
        );

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Void> deleteClub(final String req){
        Club obj = clubRepository.findByTitle(req)
                .orElseThrow(()-> new RuntimeException("There is no " + req + " club."));

        clubRepository.delete(obj);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
