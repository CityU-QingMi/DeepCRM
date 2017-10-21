	@Test
	@SuppressWarnings("")
	public void testPutBeanInSession() throws Exception {
		String targetBeanName = "target";
		HttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.setScope(WebApplicationContext.SCOPE_SESSION);
		bd.getPropertyValues().add("name", "abc");
		wac.registerBeanDefinition(targetBeanName, bd);
		wac.refresh();

		TestBean target = (TestBean) wac.getBean(targetBeanName);
		assertEquals("abc", target.getName());
		assertSame(target, request.getSession().getAttribute(targetBeanName));

		RequestContextHolder.setRequestAttributes(null);
		try {
			wac.getBean(targetBeanName);
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
		}


	}
