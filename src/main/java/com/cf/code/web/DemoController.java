/**
 * 
 */
package com.cf.code.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 *
 */
@Controller("DemoController")
@RequestMapping("/demo")
public class DemoController {
//	
//	private static Logger log = LogManager.getLogger(DemoController.class);
//	
//	@RequestMapping(value = {""}, method = { RequestMethod.GET})
//	@ResponseBody
//    public Object list(@RequestParam(required = false) String timeStr) {
//		Date time = null;
//		if(StringUtil.isNullOrEmpty(timeStr)){
//			time = DateUtil.toParse(timeStr);
//		}
//        return demoService.query(time);
//    }
//	
//	@RequestMapping(value = {""}, method = { RequestMethod.POST})
//	@ResponseBody
//    public Object create(@RequestParam(required = true) String name,Model model) {
//		Integer id = this.demoService.insert(name);
//        model.addAttribute("create-id", id);  
//        return model;
//    }
//
//	@RequestMapping(value = {"/{id}"}, method = { RequestMethod.GET})
//	@ResponseBody
//    public Object get(@PathVariable Integer id) {
//		log.info("DemoController["+id+"]-"+this);
//		Demo demo1 = this.demoService.find(id);
//        return demo1;
//    }
//	
//	@RequestMapping(value = {"/{id}"}, method = { RequestMethod.DELETE})
//	@ResponseBody
//    public Object delete(@PathVariable Integer id,Model model) {
//		boolean b = this.demoService.delete(id);
//        model.addAttribute("del-id", b+"");  
//        return model;
//    }
//	
//	@RequestMapping(value = {"/{id}"}, method = { RequestMethod.PUT})
//	@ResponseBody
//    public Object update(@PathVariable Integer id,@RequestParam(required = true) String name,Model model) {
//		try {
//			this.demoService.update(id, name);
//			 model.addAttribute("update-id", id+"");  
//		} catch (BusinessException e) {
//			 model.addAttribute("update-id-error", e.getMessage());
//		}
//        return model;
//    }
//	
//	@AccessVerifier
//	@RequestMapping(value = {"/testProfile"}, method = { RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//    public Object testProfile(@RequestParam(required = false)Profile profile,HttpSession session){
//		return profile;
//    }
//	
//	@Autowired  
//	private HttpSession session1;
//	
//	@AccessVerifier(check=false)
//	@RequestMapping(value = {"/testProfile2"}, method = { RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//    public Object testProfile2(@RequestParam(required = false)Profile profile,HttpSession session){
//		return profile;
//    }
//	
//	@RequestMapping(value = {"/testAsync"}, method = { RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//    public Object testAsync(HttpSession session,Model model) {
//		this.demoService.it4Async();
//		System.out.println("--------testAsync-------");
//		HttpSession session2 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//		System.out.println(session.getId()+","+session1.getId()+","+session2.getId());
//		System.out.println(session.getAttribute("profile"));
//		System.out.println(session1.getAttribute("profile"));
//		System.out.println(session2.getAttribute("profile"));
//		return model;
//    }
//	
//	@RequestMapping(value = {"/upload"}, method = { RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//    public Object upload(Model model,
//    		@RequestParam(value = "file", required = false) Object fileObj,
//    		@RequestParam(required = false) Integer sign) throws IllegalStateException, IOException {
//		MultipartFile file = null;
//		if(fileObj instanceof MultipartFile){
//			file = (MultipartFile)fileObj;
//			file.transferTo(new File("E:\\apache-tomcat-7.0.53-windows-x64\\image\\"+file.getOriginalFilename()));
//		}
//		log.info(file);
//		log.info(sign);
//        return model;
//    }
//	
//	@RequestMapping(value = {"/crossdomain/convert"}, method = { RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//    public Object crossdomainConvert(Model model,HttpServletRequest request,
//    		@RequestParam(value = "remoteUrl", required = true) String remoteUrl) throws MsgSendException{
////		HttpPostMsgSender sender = new HttpPostMsgSender();
////        return sender.send(remoteUrl, null);
//	    return null;
//    }
//	
}
