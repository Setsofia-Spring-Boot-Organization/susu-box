package com.backend.susu_box.features.box;

import com.backend.susu_box.core.dao.Response;
import com.backend.susu_box.core.exception.SusuBoxException;
import com.backend.susu_box.core.exception.SusuBoxExceptionMessages;
import com.backend.susu_box.core.users.user.NewBoxDto;
import com.backend.susu_box.core.users.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService{

    private final BoxUtil boxUtil;
    private final UserUtil userUtil;

    @Override
    public ResponseEntity<Response<BoxDataDao>> getBoxDataByMember(String boxId, String memberId) {

        BoxEntity box = boxUtil.getBoxById(boxId);

        if (boxUtil.userIsBoxMember(boxId, memberId)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    Response.<BoxDataDao>builder()
                            .message("Box data")
                            .data(boxUtil.buildBoxDataResponse(box))
                            .build()
            );
        } else throw new SusuBoxException(SusuBoxExceptionMessages.UNAUTHORIZED_USER);
    }


    @Override
    public ResponseEntity<Response<BoxDataDao>> createBox(String boxAdminId, NewBoxDto boxDto) {

        if (!userUtil.userIsAdmin(boxAdminId)) throw new SusuBoxException(SusuBoxExceptionMessages.UNAUTHORIZED_USER);

        return null;
    }
}
