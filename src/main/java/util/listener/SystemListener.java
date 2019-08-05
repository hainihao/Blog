package util.listener;

import com.tulun.util.config.PropertiesFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @desc	系统监听器
 * @author	ljt
 * @time	2014-6-23 下午5:17:22
 */
public class SystemListener implements ServletContextListener{
	
	private static ServletContext context;
	
	public static ServletContext getContext(){
		return context;
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		
	}
	
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		initConfig(event);
	}

	
	private void initConfig(ServletContextEvent event) {
		// 读取主配置文件  这里可以把properties和xml配置文件统一放在一个xml中，遍历加载
		PropertiesFactory.addConfiguration("sysconfig.properties");
	}
	
}
