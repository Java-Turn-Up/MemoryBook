package com.example.memorybook.service;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.entity.Club;
import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.req.ReqFormatClub;
import com.example.memorybook.model.res.ResFormatBook;
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

    public ResFormatClub.ResFormatBasicClubInfo getClubInfo(final String _cname){
        Club _club = clubRepository.findByTitle(_cname)
                .orElseThrow(() -> new RuntimeException("There is no " + _cname + " exists"));

        return ResFormatClub.ResFormatBasicClubInfo.builder()
                .clubName(_club.getTitle())
                .clubInfo(_club.getInfo())
                .build();
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

    public ResponseEntity<Void> deleteClub(final String req){
        Club obj = clubRepository.findByTitle(req)
                .orElseThrow(()-> new RuntimeException("There is no " + req + " club."));

        clubRepository.delete(obj);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ResFormatClub.BasicClubMemberInfo> getMembers(final Long clubId){
        return clubRepository.findById(clubId).orElseThrow(()->new RuntimeException("There's no " + clubId + " exists."))
                .getSigned().stream()
                .map(
                        (e) -> ( ResFormatClub.BasicClubMemberInfo.builder()
                                .memPhone(e.getPhone())
                                .memNickName(e.getNickname())
                                .memLastName(e.getLastname())
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
                                .postingBook(e.getBook_id())
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
