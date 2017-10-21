	@Test
	public void testDelegateReturnsThisIsMassagedToReturnProxy() {
		NestedTestBean target = new NestedTestBean();
		String company = "Interface21";
		target.setCompany(company);
		TestBean delegate = new TestBean() {
			@Override
			public ITestBean getSpouse() {
				return this;
			}
		};
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new DelegatingIntroductionInterceptor(delegate));
		INestedTestBean proxy = (INestedTestBean) pf.getProxy();

		assertEquals(company, proxy.getCompany());
		ITestBean introduction = (ITestBean) proxy;
		assertSame("Introduced method returning delegate returns proxy", introduction, introduction.getSpouse());
		assertTrue("Introduced method returning delegate returns proxy", AopUtils.isAopProxy(introduction.getSpouse()));
	}
