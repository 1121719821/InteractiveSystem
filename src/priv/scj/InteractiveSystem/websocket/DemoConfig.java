package priv.scj.InteractiveSystem.websocket;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
/**
 * 实现ServerApplicationConfig接口，项目启动后自动执行
 * @author JunLinTianXia
 *
 */
public class DemoConfig implements ServerApplicationConfig {

	//通过注解方式启动，扫描src下所有带@ServerEngPoint注解的类
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scan) {

		//System.out.println("Config ..." + scan.size());
		return scan;
	}

	public Set<ServerEndpointConfig> getEndpointConfigs(
			Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
