package com.backend.susu_box.features.box;

import com.backend.susu_box.core.dao.Response;
import com.backend.susu_box.core.users.user.NewBoxDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/boxes")
public class BoxController {

    private final BoxService boxService;


    @GetMapping(path = "/{box-id}")
    public ResponseEntity<Response<BoxDataDao>> getBoxDataByMember(@PathVariable("box-id") String boxId, @RequestParam String memberId) {
        return boxService.getBoxDataByMember(boxId, memberId);
    }

    /// Todo: finish the service implementation
    @PostMapping(path = "/new/{box-admin-id}")
    public ResponseEntity<Response<BoxDataDao>> createBox(
            @PathVariable("box-admin-id") String boxAdminId,
            @RequestBody NewBoxDto boxDto
    ) {
        return boxService.createBox(boxAdminId, boxDto);
    }
}
