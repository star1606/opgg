package com.cos.opgg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.cos.opgg.api.dto.InfoDto;
import com.cos.opgg.dto.CommunityDto;
import com.cos.opgg.dto.RespDto;
import com.cos.opgg.model.Post;
import com.cos.opgg.model.Reply;
import com.cos.opgg.repository.PostRepository;
import com.cos.opgg.repository.ReplyRepository;
import com.cos.opgg.repository.SummonerRepository;
import com.cos.opgg.service.ApiService;

@RestController
public class TestController {

	@Autowired
	SummonerRepository summonerRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	ReplyRepository replyRepostory;
	@Autowired
	ApiService apiService;

	
	
	//상세보기 
	@GetMapping("/test/post/detail/{postId}")
	public RespDto<?> testDetail(@PathVariable int postId){
		
		Post post = postRepository.findById(postId);
		CommunityDto communityDto = CommunityDto.builder()
				.type(1)
				.post(post)
				.build();
		
		
		return new RespDto<CommunityDto>(HttpStatus.OK.value(), "정상" , communityDto);
	}
	//글전체 보기
	@GetMapping("/test/post/{page}")
	public RespDto<?> testCommuniy(@PathVariable int page) {

		PageRequest pageRequest = PageRequest.of(0, 40, Sort.by(Direction.DESC, "id"));

		Page<Post> pagePost = postRepository.findAll(pageRequest);
		List<Post> posts = pagePost.getContent();
		

		List<CommunityDto> communityDtos = new ArrayList<>();

		for (Post post : posts) {

			CommunityDto communityDto = CommunityDto.builder().type(1).post(post).build();

			communityDtos.add(communityDto);

		}
		
		if(posts.size() < 40) {
			
			CommunityDto communityDtoFooter = CommunityDto.builder().type(2).build();

			communityDtos.add(communityDtoFooter);
			
			return new RespDto<List<CommunityDto>>(HttpStatus.NO_CONTENT.value(),"더이상 값이 없습니다.",communityDtos);
		}

		CommunityDto communityDtoFooter = CommunityDto.builder().type(2).build();

		communityDtos.add(communityDtoFooter);
		
		if(page == 0) {
			return new RespDto<List<CommunityDto>>(HttpStatus.CREATED.value(),"정상",communityDtos);
		}

		return new RespDto<List<CommunityDto>>(HttpStatus.OK.value(),"정상",communityDtos);
	}

	// rankingDto 가져오기 아이디 검색
	@GetMapping("test/ranking/name/{name}")
	public RespDto<?> getRankByName(@PathVariable String name) {

		return apiService.getRank(name);
	}

	// rankingDto 가져오기
	@GetMapping("test/ranking/page/{page}")
	public RespDto<?> getRankByPage(@PathVariable long page) {

		return apiService.getRank(page);
	}

	// detailDto 가져오기
	@GetMapping("test/detail/gameid/{gameId}")
	public RespDto<?> getDetailByGameId(@PathVariable long gameId) {

		return apiService.getDetail(gameId);
	}

	// infoDto 가져오기
	@GetMapping("test/info/name/{name}")
	public RespDto<?> getInfoByName(@PathVariable String name) {

		return apiService.getInfo(name);
	}

	// 전적갱신하기
	@GetMapping("test/info/update/name/{name}")
	public RespDto<?> updateInfoByName(@PathVariable String name) {

		boolean isGetData = apiService.getApiData(name);

		if (isGetData) {
			return apiService.getInfo(name);
		} else {
			return new RespDto<List<InfoDto>>(HttpStatus.BAD_REQUEST.value(), "전적갱신에 실패하였습니다.", null);
		}

	}

	@GetMapping("test/input/123123123/{name}")
	public String testInput(@PathVariable String name) {

		boolean b = apiService.getApiData(name);

		if (b) {
			return "입력성공";
		} else {
			return "입력실패";
		}
	}

	@GetMapping("test/getallrank/97987987979797987987")
	public String getAllRank() {

		apiService.getAllRank();

		return "clear";
	}

	// 커뮤니티 글목록 보기
	@GetMapping("/test/post")
	public List<Post> PostTest() {
		List<Post> post = postRepository.findAll();

		return post;
	}

	// 커뮤니티 글 상세보기
//	@GetMapping("/test/post/{id}")
//	public Post ttttt(@PathVariable int id) {
//		Post post = postRepository.findById(id);
//		return post;
//
//	}

	// 커뮤니티 글 검색
	@GetMapping("/test/post/search/{content}")
	public List<Post> seach(@PathVariable(name = "content") String content) {
		List<Post> post = postRepository.search(content);
		System.out.println(post);
		return post;
	}

	@GetMapping("/reply")
	public List<Reply> find() {
		List<Reply> reply = replyRepostory.findAll();
		return reply;
	}

}