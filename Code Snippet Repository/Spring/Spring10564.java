	@Test
	public void getFromFactoryBeanInScope() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		String name = "requestScopedFactoryBean";
		assertNull(request.getAttribute(name));
		TestBean bean = (TestBean) this.beanFactory.getBean(name);
		assertTrue(request.getAttribute(name) instanceof FactoryBean);
		assertSame(bean, this.beanFactory.getBean(name));
	}
