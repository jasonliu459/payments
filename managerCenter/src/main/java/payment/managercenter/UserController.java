package payment.managercenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import paymentDomain.ctcenter.User;

@Controller
public class UserController {
	private static Log log = LogFactory.getLog(UserController.class);
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="user/single/{id:\\d+}.do",method=RequestMethod.GET)
	public @ResponseBody 
	User getUserById(@PathVariable("id") Long id){
		log.info("get user id is:"+id);
		User user = new User();
		user.setUserId(id);
		user.setUserName("testName");
		user.setUserPwd("123");
		return user;
	}
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="user/all.do",method=RequestMethod.GET)
	public @ResponseBody 
	List<User> getUserList(){
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setUserId(1L);
		user.setUserName("testName1");
		user.setUserPwd("1");
		User user2 = new User();
		user2.setUserId(2L);
		user2.setUserName("testName2");
		user2.setUserPwd("2");
		User user3 = new User();
		user3.setUserId(3L);
		user3.setUserName("testName");
		user3.setUserPwd("3");
		list.add(user);
		list.add(user2);
		list.add(user3);
		return list;
	}
}
