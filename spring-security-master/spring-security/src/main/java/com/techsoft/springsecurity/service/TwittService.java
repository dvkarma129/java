package com.techsoft.springsecurity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techsoft.springsecurity.entity.Tweet;
import com.techsoft.springsecurity.entity.UserInfo;
import com.techsoft.springsecurity.repository.TweetRepository;
import com.techsoft.springsecurity.repository.UserInfoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class TwittService {
	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public int getMyId(String userName) {
		UserInfo user = userInfoRepository.findByName(userName).get();
		return user.getId();
	}

	public List<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}

	public List<Tweet> getMyTwitts(int id) {
		List<Tweet> tweet = tweetRepository.findAllByUser(userInfoRepository.findById(id).get());
		return tweet;
	}

	public Tweet newTweet(Tweet model, int id) {
		Tweet tweet = new Tweet();
		tweet.setUser(userInfoRepository.findById(id).get());
		tweet.setContent(model.getContent());
		tweet.setCreatedAt(LocalDateTime.now());
		tweet.setHashtag(model.getHashtag());
		tweetRepository.save(tweet);
		return tweet;
	}

	public Tweet updateTwitt(Tweet model, Long tid) {
		Tweet tweet = tweetRepository.findById(tid).get();
		tweet.setContent(model.getContent());
		tweet.setHashtag(model.getHashtag());
		tweet.setCreatedAt(LocalDateTime.now());

		return tweetRepository.save(tweet);
	}

	public void deleteTwitt(Long tid) {
		tweetRepository.deleteById(tid);
	}

	public void removeTwitts(Long tid) {
		tweetRepository.deleteById(tid);
	}

	public List<Tweet> getTwittsByHashtags(String hash) {
		hash.equalsIgnoreCase(hash);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tweet> criteriaQuery = criteriaBuilder.createQuery(Tweet.class);
		Root<Tweet> root = criteriaQuery.from(Tweet.class);

		criteriaQuery.select(root).where(criteriaBuilder.like(root.get("hashtag"),"#"+hash+"%"));

		List<Tweet> list =  entityManager.createQuery(criteriaQuery).getResultList();
		System.out.println(list);
		return list; 
	}

}
