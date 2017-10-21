	@Test
	public void testViewControllersDefaultConfig() {
		loadBeanDefinitions("mvc-config-view-controllers-minimal.xml");

		SimpleUrlHandlerMapping hm = this.appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(hm);
		ParameterizableViewController viewController = (ParameterizableViewController) hm.getUrlMap().get("/path");
		assertNotNull(viewController);
		assertEquals("home", viewController.getViewName());

		ParameterizableViewController redirectViewController = (ParameterizableViewController) hm.getUrlMap().get("/old");
		assertNotNull(redirectViewController);
		assertThat(redirectViewController.getView(), Matchers.instanceOf(RedirectView.class));

		ParameterizableViewController statusViewController = (ParameterizableViewController) hm.getUrlMap().get("/bad");
		assertNotNull(statusViewController);
		assertEquals(404, statusViewController.getStatusCode().value());

		BeanNameUrlHandlerMapping beanNameMapping = this.appContext.getBean(BeanNameUrlHandlerMapping.class);
		assertNotNull(beanNameMapping);
		assertEquals(2, beanNameMapping.getOrder());
	}
