	@Test
	public void testRequestScoping() throws Exception {
		MockHttpServletRequest oldRequest = new MockHttpServletRequest();
		MockHttpServletRequest newRequest = new MockHttpServletRequest();

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(oldRequest));

		ITestBean scoped = (ITestBean) this.context.getBean("requestScoped");
		assertTrue("Should be AOP proxy", AopUtils.isAopProxy(scoped));
		assertTrue("Should be target class proxy", scoped instanceof TestBean);

		ITestBean testBean = (ITestBean) this.context.getBean("testBean");
		assertTrue("Should be AOP proxy", AopUtils.isAopProxy(testBean));
		assertFalse("Regular bean should be JDK proxy", testBean instanceof TestBean);

		String rob = "Rob Harrop";
		String bram = "Bram Smeets";

		assertEquals(rob, scoped.getName());
		scoped.setName(bram);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(newRequest));
		assertEquals(rob, scoped.getName());
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(oldRequest));
		assertEquals(bram, scoped.getName());

		assertTrue("Should have advisors", ((Advised) scoped).getAdvisors().length > 0);
	}
