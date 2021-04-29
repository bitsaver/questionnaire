package pers.yang.questionnaire.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

@Component
public class CheckCodeUtil {

	private final static HashMap<String,Integer> codeMap = new HashMap<>();

	@Resource
	EmailUtil emailUtil;

	/**
	 * 生成并保存验证码，然后通过邮箱将验证码发送给用户
	 * @param email 电子邮件
	 */
	public Boolean genAndsend(String email) throws MessagingException {
		//生成6位伪随机数
		int checkCode = (int) ((Math.random()*9+1)*100000);
		//保存随机数到codeMap中
		codeMap.put(email,checkCode);
		//生成失效时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 5);
		String expireTime = sdf.format(nowTime.getTime());

		String text = "<html><body><p>您好！</br></br>感谢您对问卷调查和数据分析系统的支持。请回填如下验证码：</br></br><b style='color:blue'>"
				+ checkCode
				+ "</b></br></br>"
				+ "验证码于"+expireTime + "前有效。请勿将验证码提供给他人。<p></body></html>";
		//发给邮件
		return emailUtil.send(email,"验证码",text);
	}

	public Integer getByEmail(String id) {
		return codeMap.get(id);
	}
}
