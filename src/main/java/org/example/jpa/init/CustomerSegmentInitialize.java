package org.example.jpa.init;

import jakarta.annotation.PostConstruct;
import org.example.jpa.domain.CustomerSegment;
import org.example.jpa.repository.CustomerSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void init() {

        if (customerSegmentRepository.count() == 0) {
            CustomerSegment segmentRegular = new CustomerSegment();
            segmentRegular.setSegment("REGULAR");
            segmentRegular.setDescription("REGULAR");
            segmentRegular.setIsDeleted(false);

            CustomerSegment segmentSilver = new CustomerSegment();
            segmentSilver.setSegment("SILVER");
            segmentSilver.setDescription("SILVER");
            segmentSilver.setIsDeleted(false);

            CustomerSegment segmentGold = new CustomerSegment();
            segmentGold.setSegment("GOLD");
            segmentGold.setDescription("GOLD");
            segmentGold.setIsDeleted(false);

            customerSegmentRepository.saveAll(
                    List.of(segmentRegular, segmentSilver, segmentGold)
            );
        }

    }

}