	@Test
	public void orderOnImplementationWithProxy() {
		this.context = new AnnotationConfigApplicationContext(
				UserServiceTwo.class, UserServiceOne.class, UserServiceCollector.class, AsyncConfig.class);

		// Validate those beans are indeed wrapped by a proxy
		UserService serviceOne = this.context.getBean("serviceOne", UserService.class);
		UserService serviceTwo = this.context.getBean("serviceTwo", UserService.class);
		assertTrue(AopUtils.isAopProxy(serviceOne));
		assertTrue(AopUtils.isAopProxy(serviceTwo));

		UserServiceCollector bean = this.context.getBean(UserServiceCollector.class);
		assertSame(serviceOne, bean.userServices.get(0));
		assertSame(serviceTwo, bean.userServices.get(1));
	}
