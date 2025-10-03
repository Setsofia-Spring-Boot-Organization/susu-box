package com.backend.susu_box.features.box;

import com.backend.susu_box.core.dao.Response;
import com.backend.susu_box.core.users.user.NewBoxDto;
import org.springframework.http.ResponseEntity;

public interface BoxService {

    ResponseEntity<Response<BoxDataDao>> createBox(String boxAdminId, NewBoxDto boxDto);

    ResponseEntity<Response<BoxDataDao>> getBoxDataByMember(String boxId, String memberId);
}
