	@Test
	public void requestScopedInnerBeanDestroyedWhileContainedBySingleton() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		String outerBeanName = "singletonOuterBean";
		TestBean outer1 = (TestBean) this.beanFactory.getBean(outerBeanName);
		assertNull(request.getAttribute(outerBeanName));
		TestBean inner1 = (TestBean) outer1.getSpouse();
		TestBean outer2 = (TestBean) this.beanFactory.getBean(outerBeanName);
		assertSame(outer1, outer2);
		assertSame(inner1, outer2.getSpouse());
		requestAttributes.requestCompleted();
		assertTrue(inner1.wasDestroyed());
		assertFalse(outer1.wasDestroyed());
	}
