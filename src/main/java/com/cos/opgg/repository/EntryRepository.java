package com.cos.opgg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.opgg.api.model.EntryModel;

public interface EntryRepository extends JpaRepository<EntryModel, Integer>{
	
	@Query(value = "SELECT id, leagueId, leaguePoints, losses, queueType, rank, summonerId, summonerName, tier, tierRankId, wins   FROM entrymodel WHERE LOWER(REPLACE(summonerName,' ', '')) LIKE ?1", nativeQuery = true)
	List<EntryModel> findAllBySummonerName(String summonerName);
	
	List<EntryModel> findAllBySummonerId(String summonerId);
	
	EntryModel findBySummonerIdAndQueueType(String summonerId, String queueType);
}
