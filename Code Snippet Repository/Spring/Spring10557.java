	@Test
	@SuppressWarnings("")
	public void testPutBeanInRequest() throws Exception {
		String targetBeanName = "target";

		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.setScope(WebApplicationContext.SCOPE_REQUEST);
		bd.getPropertyValues().add("name", "abc");
		wac.registerBeanDefinition(targetBeanName, bd);
		wac.refresh();

		HttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TestBean target = (TestBean) wac.getBean(targetBeanName);
		assertEquals("abc", target.getName());
		assertSame(target, request.getAttribute(targetBeanName));

		TestBean target2 = (TestBean) wac.getBean(targetBeanName);
		assertEquals("abc", target2.getName());
		assertSame(target2, target);
		assertSame(target2, request.getAttribute(targetBeanName));

		request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TestBean target3 = (TestBean) wac.getBean(targetBeanName);
		assertEquals("abc", target3.getName());
		assertSame(target3, request.getAttribute(targetBeanName));
		assertNotSame(target3, target);

		RequestContextHolder.setRequestAttributes(null);
		try {
			wac.getBean(targetBeanName);
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
		}
	}
