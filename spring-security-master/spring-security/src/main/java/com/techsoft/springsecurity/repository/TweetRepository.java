package com.techsoft.springsecurity.repository;

import com.techsoft.springsecurity.entity.Tweet;
import com.techsoft.springsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {


	List<Tweet> findAllByUser(UserInfo userInfo);

}
