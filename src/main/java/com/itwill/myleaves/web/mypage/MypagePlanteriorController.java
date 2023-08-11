package com.itwill.myleaves.web.mypage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.myleaves.dto.planterior.PlanteriorUpdateDto;
import com.itwill.myleaves.repository.planterior.Bookmark;
import com.itwill.myleaves.repository.planterior.BookmarkRepository;
import com.itwill.myleaves.repository.planterior.Planterior;
import com.itwill.myleaves.repository.planterior.PlanteriorRepository;
import com.itwill.myleaves.service.palnterior.BookmarkService;
import com.itwill.myleaves.service.palnterior.CategoryService;
import com.itwill.myleaves.service.palnterior.MypageService;
import com.itwill.myleaves.service.palnterior.PlanteriorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/planterior")
public class MypagePlanteriorController {
	
	private final MypageService mypageService;
	private final BookmarkService bookmarkService;
	private final CategoryService categoryService;
	
	// 내가 쓴 글 읽기
	@GetMapping("/my_posts")
	public String planteriorMine(Model model, String userId ) {
		log.info("planteriorMine(userId = {})", userId);
		
		List<Planterior> list = mypageService.read(userId);
		
		model.addAttribute("cardList", list);
		
		return "mypage/planterior/planteriorMyposts";
	}
	
	// 내가 쓴 글 수정
	@GetMapping("/updateDelete")
	public void planteriorUpdate(Model model, Long planteriorId) {
		log.info("planteriorUpdate(planteriorId={})", planteriorId);
		
		Planterior list = mypageService.read(planteriorId);
		
		model.addAttribute("cardList", list);
	}
	
	@PostMapping("/update")
	public String update(PlanteriorUpdateDto dto) {
		log.info("update(dto ={})", dto);
		
		mypageService.update(dto);
		return "redirect:/mypage/planterior/my_posts?userId=" + dto.getUserId();
	}
	
	@PostMapping("delete")
	public String delete(long planteriorId, String userId) {
		log.info("delete(planteriorId = {})", planteriorId);
		
		mypageService.delete(planteriorId);
		return "redirect:/mypage/planterior/my_posts?userId=" + userId;
	}
	
	// 북마크
	@GetMapping("bookmark")
	public void bookmarkRead(Model model, String userId) {
		log.info("bookmarkRead");
		
		// 북마크 가져오기
		List<Bookmark> list = mypageService.bookmarkRead(userId);
		log.info("sizeb={}",list.size());
		
		// 그 List와 플랜테리어 아이디비교해서 값 가져오기
		List<Planterior> plist = mypageService.read();
		log.info("sizep={}",plist.size());
		
		// 보낼 list
		List<Planterior> result = new ArrayList<>();

		
		for(int i = 0; i < plist.size(); i++) {
			for(int j = 0; j<list.size(); j++) {
				if(plist.get(i).getPlanteriorId() == list.get(j).getPlanteriorId()) {
					log.info("result = {}", plist.get(i));
					result.add(plist.get(i));
				}
			}
		}
		log.info("size={}",result.size());
		
		model.addAttribute("cardList", result);
	}
	
	
}
