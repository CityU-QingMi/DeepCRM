	@Test
	public void testGetFromFactoryBeanInScope() throws Exception {
		String name = "requestScopedFactoryBean";
		TestBean bean = (TestBean) this.beanFactory.getBean(name);
		assertTrue(AopUtils.isCglibProxy(bean));

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		try {
			assertNull(request.getAttribute("scopedTarget." + name));
			assertEquals(DummyFactory.SINGLETON_NAME, bean.getName());
			assertNotNull(request.getAttribute("scopedTarget." + name));
			assertEquals(DummyFactory.class, request.getAttribute("scopedTarget." + name).getClass());
			assertSame(bean, this.beanFactory.getBean(name));
		}
		finally {
			RequestContextHolder.setRequestAttributes(null);
		}
	}
