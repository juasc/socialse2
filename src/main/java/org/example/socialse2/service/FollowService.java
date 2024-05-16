package org.example.socialse2.service;

import org.example.socialse2.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public int getFollowingCount(Long userId) {
        return followRepository.countByFollowerId(userId);
    }

    public int getFollowerCount(Long userId) {
        return followRepository.countByFollowingId(userId);
    }
}