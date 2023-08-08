package wowmarket.wow_server.mypage.myproject.mysales.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wowmarket.wow_server.mypage.myproject.dto.MySalesDetailResponseDto;
import wowmarket.wow_server.mypage.myproject.dto.MySalesListResponseDto;
import wowmarket.wow_server.mypage.myproject.mysales.service.MySalesProjectService;

@RestController
@RequestMapping("/wowmarket/mypage/myproject")
@RequiredArgsConstructor
public class MySalesProjectController {
    private final MySalesProjectService mySalesProjectService;

    @GetMapping()
    public MySalesListResponseDto getMySalesList(@RequestParam(value = "page", defaultValue = "1", required = false)int page){
        Pageable pageable = PageRequest.of(page - 1, 10);
        return mySalesProjectService.findAllMySalesForm(pageable);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity finishMySales(@PathVariable Long project_id){
        mySalesProjectService.finishMySalesForm(project_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/detail/{project_id}")
    public MySalesDetailResponseDto getMySalesDetail(@PathVariable Long project_id){
        return mySalesProjectService.findMySalesDetail(project_id);
    }

}
