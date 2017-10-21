	@Override
	protected ConfigurableApplicationContext createContext() throws Exception {
		InitAndIB.constructed = false;
		root = new XmlWebApplicationContext();
		root.getEnvironment().addActiveProfile("rootProfile1");
		MockServletContext sc = new MockServletContext("");
		root.setServletContext(sc);
		root.setConfigLocations(new String[] {"/org/springframework/web/context/WEB-INF/applicationContext.xml"});
		root.addBeanFactoryPostProcessor(new BeanFactoryPostProcessor() {
			@Override
			public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
				beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
					@Override
					public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
						if (bean instanceof TestBean) {
							((TestBean) bean).getFriends().add("myFriend");
						}
						return bean;
					}
					@Override
					public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
						return bean;
					}
				});
			}
		});
		root.refresh();
		XmlWebApplicationContext wac = new XmlWebApplicationContext();
		wac.getEnvironment().addActiveProfile("wacProfile1");
		wac.setParent(root);
		wac.setServletContext(sc);
		wac.setNamespace("test-servlet");
		wac.setConfigLocations(new String[] {"/org/springframework/web/context/WEB-INF/test-servlet.xml"});
		wac.refresh();
		return wac;
	}
