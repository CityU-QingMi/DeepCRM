	@Test
	public void testSessionScoping() throws Exception {
		MockHttpSession oldSession = new MockHttpSession();
		MockHttpSession newSession = new MockHttpSession();

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(oldSession);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ITestBean scoped = (ITestBean) this.context.getBean("sessionScoped");
		assertTrue("Should be AOP proxy", AopUtils.isAopProxy(scoped));
		assertFalse("Should not be target class proxy", scoped instanceof TestBean);

		ITestBean scopedAlias = (ITestBean) this.context.getBean("sessionScopedAlias");
		assertSame(scoped, scopedAlias);

		ITestBean testBean = (ITestBean) this.context.getBean("testBean");
		assertTrue("Should be AOP proxy", AopUtils.isAopProxy(testBean));
		assertFalse("Regular bean should be JDK proxy", testBean instanceof TestBean);

		String rob = "Rob Harrop";
		String bram = "Bram Smeets";

		assertEquals(rob, scoped.getName());
		scoped.setName(bram);
		request.setSession(newSession);
		assertEquals(rob, scoped.getName());
		request.setSession(oldSession);
		assertEquals(bram, scoped.getName());

		assertTrue("Should have advisors", ((Advised) scoped).getAdvisors().length > 0);
	}
