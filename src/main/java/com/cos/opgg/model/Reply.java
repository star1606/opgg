package com.cos.opgg.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "post"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "reply")
	private String reply;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({"password"})
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	@JsonIgnoreProperties({"replies", "user.password"}) // post안에있는 replies안가져오기
	private Post post;
	
	@CreationTimestamp
	@Column(name = "createDate")
	private Timestamp createDate;
	
}
