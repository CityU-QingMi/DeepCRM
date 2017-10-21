	@Test
	public void innerBeanInheritsContainingBeanScopeByDefault() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		String outerBeanName = "requestScopedOuterBean";
		assertNull(request.getAttribute(outerBeanName));
		TestBean outer1 = (TestBean) this.beanFactory.getBean(outerBeanName);
		assertNotNull(request.getAttribute(outerBeanName));
		TestBean inner1 = (TestBean) outer1.getSpouse();
		assertSame(outer1, this.beanFactory.getBean(outerBeanName));
		requestAttributes.requestCompleted();
		assertTrue(outer1.wasDestroyed());
		assertTrue(inner1.wasDestroyed());
		request = new MockHttpServletRequest();
		requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);
		TestBean outer2 = (TestBean) this.beanFactory.getBean(outerBeanName);
		assertNotSame(outer1, outer2);
		assertNotSame(inner1, outer2.getSpouse());
	}
