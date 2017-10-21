	@Test
	public void testProcessInjectionBasedOnServletContext() {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(wac);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "tb");
		wac.registerSingleton("testBean", TestBean.class, pvs);

		MockServletContext sc = new MockServletContext();
		wac.setServletContext(sc);
		wac.refresh();
		sc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		InjectionTarget target = new InjectionTarget();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(target, sc);
		assertTrue(target.testBean instanceof TestBean);
		assertEquals("tb", target.name);
	}
