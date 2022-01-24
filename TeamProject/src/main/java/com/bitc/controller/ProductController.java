package com.bitc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.dto.ProductDto;
import com.bitc.service.ProductService;
import com.github.pagehelper.PageInfo;

@Controller
public class ProductController {

	@Autowired
	private ProductService projectService;
	
	/*
//	메인화면
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() throws Exception {
		return "/product/Main";
	}
	*/
//	메뉴리스트
	@RequestMapping(value="/List", method=RequestMethod.GET)
	public ModelAndView openMenuList() throws Exception {
		ModelAndView mv = new ModelAndView("/product/Menu");
		
		List<ProductDto> menuList = projectService.selectMenuList();
		mv.addObject("menuList", menuList);
		
		return mv;
	}
	
//	장바구니 리스트
	@RequestMapping(value="/basket", method=RequestMethod.GET)
	public ModelAndView openBasketList() throws Exception {
		ModelAndView mv = new ModelAndView("/product/Basket");
		
		List<ProductDto> basketList = projectService.selectBasketList();
		mv.addObject("basketList", basketList);
		
		return mv;
	}
	
//	메뉴 페이징
	@RequestMapping("/menu/{shopName}")
	public ModelAndView paging(@RequestParam(required = false, defaultValue="1", value="pageNum") int pageNum, @PathVariable(value="shopName") String shopName) throws Exception {
		ModelAndView mv = new ModelAndView("/product/Menu");
		
		PageInfo<ProductDto> page = new PageInfo<>(projectService.selectMenuPageList(pageNum, shopName), 3);
		
		mv.addObject("pages", page);
		mv.addObject("shopName", shopName);

		return mv;
	}
	
//	장바구니 페이징
	@RequestMapping("/basketPaging")
	public ModelAndView basketListPage(@RequestParam(required = false, defaultValue="1") int pageNum) throws Exception {
		ModelAndView mv = new ModelAndView("/product/Basket");
		
		PageInfo<ProductDto> paging = new PageInfo<>(projectService.selectBasketPageList(pageNum), 3);
		
		mv.addObject("basketPage", paging);
		
		return mv;
	}
	
//	제품 주문 리스트 화면
	@RequestMapping(value="/pay", method=RequestMethod.GET)
	public String pay() throws Exception {
		return "/product/Pay";
	}
	
//	주문->장바구니 담기
	@ResponseBody
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public Map<String, String> insertMenu(ProductDto shop) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		
		int count = projectService.insertMenu(shop); // 데이터 입력
		
		if (count != 0) {
			result.put("result", "success");
		}
		else {
			result.put("result", "error");
		}
		return result;
	}
	
//	장바구니에서 메뉴 삭제
	@RequestMapping(value="/delete/{no}", method=RequestMethod.DELETE)
	public String deleteMenu(@PathVariable("no") int no) throws Exception {
		projectService.deleteMenu(no);
		return "redirect:/basketPaging";
	}
	
//	주문 제품 리스트
	@RequestMapping(value="/price", method=RequestMethod.GET)
	public ModelAndView openPriceList() throws Exception {
		ModelAndView mv = new ModelAndView("/product/Pay");
		
		List<ProductDto> priceList = projectService.selectPriceList();
		
		int total = 0;
		
		for (ProductDto shop : priceList) {
			total += Integer.parseInt(shop.getShopMprice());
		}
		
		ProductDto totalPrice = new ProductDto();
		totalPrice.setShopMenu("총합");
		totalPrice.setShopMprice("" + total);
		priceList.add(totalPrice);
		
		mv.addObject("prices", priceList);
//		mv.addObject("total", total);
		
		return mv;
	}
	
//	계산완료 후 장바구니 비우기
	@ResponseBody
	@RequestMapping(value="/clear", method=RequestMethod.DELETE)
	public String clearBasket(@RequestParam(value="no[]") int[] no) throws Exception {
	//public String clearBasket(@RequestParam(value="no") String[] no) throws Exception {
	//public String clearBasket(@PathVariable(value="no") String[] no) throws Exception {
		int count = projectService.clearBasket(no);
		
		if (count < 1) {
			return "error";
		}
		else {
			return "success";
		}
		
//		 return "redirect:/main"; 
	}
}







