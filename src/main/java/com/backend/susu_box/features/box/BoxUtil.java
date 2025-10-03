package com.backend.susu_box.features.box;

import com.backend.susu_box.core.exception.SusuBoxException;
import com.backend.susu_box.core.exception.SusuBoxExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoxUtil {

    private final BoxRepository boxRepository;

    boolean userIsBoxMember(String boxId, String userId) {

        BoxEntity box = boxRepository.findById(boxId)
                .orElseThrow(() -> new SusuBoxException(SusuBoxExceptionMessages.NOT_FOUND));

        for (BoxMember member : box.getBoxMembers()) {
            if (member.getUser().getId().equals(userId)) return true;
        }

        return false;
    }


    BoxEntity getBoxById(String id) {
        return boxRepository.findById(id)
                .orElseThrow(() -> new SusuBoxException(SusuBoxExceptionMessages.NOT_FOUND));
    }

    BoxDataDao buildBoxDataResponse(BoxEntity box) {
        return BoxDataDao
                .builder()
                .id(box.getId())
                .name(box.getName())
                .description(box.getDescription())
                .contributionAmount(box.getContributionAmount())
                .frequency(box.getFrequency().name())
                .createdBy(box.getCreatedBy())
                .boxRules(box.getBoxRules())
                .boxMembers(box.getBoxMembers())
                .createdAt(box.getCreatedAt())
                .updatedAt(box.getUpdatedAt())
                .build();
    }
}
