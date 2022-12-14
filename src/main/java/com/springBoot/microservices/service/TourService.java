package com.springBoot.microservices.service;

import com.springBoot.microservices.model.Difficulty;
import com.springBoot.microservices.model.Region;
import com.springBoot.microservices.model.Tour;
import com.springBoot.microservices.model.TourPackage;
import com.springBoot.microservices.repository.TourPackageRepository;
import com.springBoot.microservices.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final TourPackageRepository tourPackageRepository;

    public Tour createTour(String title,
                           String description,
                           String blurb,
                           Integer price,
                           String duration,
                           String bullets,
                           String keywords,
                           String tourPackageName,
                           Difficulty difficulty,
                           Region region ) {

        TourPackage tourPackage = tourPackageRepository
                .findByName(tourPackageName)
                .orElseThrow( () -> new RuntimeException("Tour package does not exist: " + tourPackageName));

        return tourRepository
                .save(new Tour(title, description,blurb, price, duration, bullets, keywords, tourPackage, difficulty, region));
    }
    public long total() {
        return tourRepository.count();
    }
}

