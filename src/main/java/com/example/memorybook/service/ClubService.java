package com.example.memorybook.service;

import com.example.memorybook.model.entity.Club;
import com.example.memorybook.model.req.ReqFormatClub;
import com.example.memorybook.model.res.ResFormatClub;
import com.example.memorybook.repository.BookRepository;
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
    private final BookRepository bookRepository;

    public ResFormatClub.ResFormatBasicClubInfo getClubById(final Long id){
        Club club = clubRepository.findById(id).orElseThrow(()-> new RuntimeException("Club not exists: " + id));
        return ResFormatClub.ResFormatBasicClubInfo.builder()
                .clubName(club.getTitle())
                .clubInfo(club.getInfo())
                .build();
    }

    public List<ResFormatClub.ResFormatBasicClubInfo> getClubList(final String _cname){
        List<Club> _club = clubRepository.findByTitle(_cname);

        return clubRepository.findByTitle(_cname).stream()
                .map( (e) -> (
                        ResFormatClub.ResFormatBasicClubInfo.builder()
                                .clubName(e.getTitle())
                                .clubInfo(e.getInfo())
                                .build())
                ).collect(Collectors.toList());
    }

    public List<ResFormatClub.ResFormatBasicClubInfo> getAllClub(){
        return clubRepository.findAll().stream()
                .map(e -> (ResFormatClub.ResFormatBasicClubInfo.builder()
                        .clubName(e.getTitle())
                        .clubInfo(e.getInfo())
                        .build()
                ))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Void> postClub(final ReqFormatClub.BasicClubInfo req){
        clubRepository.save(
                Club.builder()
                .title(req.getClubTitle())
                .info(req.getClubInfo())
                .build()
        );

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Void> deleteClub(final Long id){
        Club obj = clubRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("There is no " + id + " club."));

        clubRepository.delete(obj);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ResFormatClub.BasicClubMemberInfo> getMembers(final Long clubId){
        return clubRepository.findById(clubId).orElseThrow(()->new RuntimeException("There's no " + clubId + " exists."))
                .getSigned().stream()
                .map(
                        (e) -> ( ResFormatClub.BasicClubMemberInfo.builder()
                                .memPhone(e.getPhone())
                                .memNickName(e.getNickName())
                                .memLastName(e.getLastName())
                                .memEmail(e.getEmail())
                                .memFirstName(e.getFirstName())
                                .build()
                                )
                ).collect(Collectors.toList());
    }


    public List<ResFormatClub.BasicClubPostingInfo> getPostings(final Long clubId){
        return clubRepository.findById(clubId).orElseThrow(()->new RuntimeException("There's no " + clubId + " exists."))
                .getPosted().stream()
                .map(
                        (e) -> ( ResFormatClub.BasicClubPostingInfo.builder()
                                .postingTitle(e.getTitle())
                                .postingContent(e.getContent())
                                .postingBook(e.getBookId())
                                .postingCreator(e.getCreator())
                                .hit(e.getHit())
                                .like(e.getLike())
                                .dislike(e.getDislike())
                                .createdat(e.getCreatedat())
                                .updatedat(e.getUpdatedat())
                                .build()
                                )
                ).collect(Collectors.toList());


    }
}
