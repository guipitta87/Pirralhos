package br.com.pirralhos.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {

	private static ClassPathXmlApplicationContext ctx;

	static {
		ctx = new ClassPathXmlApplicationContext("br/com/pirralhos/config/applicationContext.xml");
	}

	private BeanFactory() {
		
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object getBean(String beanName, Class classe) {
		return ctx.getBean(beanName, classe);
	}

}
