	@Test
	public void testWithIntroduction() {
		String beanName = "foo";
		TestBean target = new RequiresBeanNameBoundTestBean(beanName);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		pf.addAdvisor(ExposeBeanNameAdvisors.createAdvisorIntroducingNamedBean(beanName));
		ITestBean proxy = (ITestBean) pf.getProxy();

		assertTrue("Introduction was made", proxy instanceof NamedBean);
		// Requires binding
		proxy.getAge();

		NamedBean nb = (NamedBean) proxy;
		assertEquals("Name returned correctly", beanName, nb.getBeanName());
	}
