package com.cos.opgg.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummonerModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(unique = true)
    private String summonerId;
	
	@Column(unique = true)
    private String accountId;
	
	@Column(unique = true)
    private String puuid;
	
    private String name;
    private long profileIconId;
    private long revisionDate;
    private long summonerLevel;

}


