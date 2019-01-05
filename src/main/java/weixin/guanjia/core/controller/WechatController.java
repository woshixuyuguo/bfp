package weixin.guanjia.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.service.impl.WechatService;
import weixin.guanjia.core.util.SignUtil;

@Controller
@RequestMapping("/wechatController")
public class WechatController {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private  Logger logger=Logger.getLogger(WechatController.class);
	@RequestMapping(params="wechat", method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr) {

		List<WeixinAccountEntity> weixinAccountEntities = weixinAccountService.getList(WeixinAccountEntity.class);
		for (WeixinAccountEntity account : weixinAccountEntities) {
			if (SignUtil.checkSignature(account.getAccounttoken(), signature,
					timestamp, nonce)) {
				try {
					response.getWriter().print(echostr);
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(params = "wechat", method = RequestMethod.POST)
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Enumeration en = request.getParameterNames();
        SortedMap<String, String> map = new TreeMap<String, String>();
        while (en.hasMoreElements()) {
            String param = (String) en.nextElement();
            map.put(param,   request.getParameter(param));
            logger.info("接收到的信息："+param+"="+map.get(param));
            request.setAttribute(param, map.get(param));
        }
		String respMessage = wechatService.coreService(request);
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}

}
