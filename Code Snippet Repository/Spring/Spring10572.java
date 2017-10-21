	@Test
	public void testGetInnerBeanFromScope() throws Exception {
		TestBean bean = (TestBean) this.beanFactory.getBean("outerBean");
		assertFalse(AopUtils.isAopProxy(bean));
		assertTrue(AopUtils.isCglibProxy(bean.getSpouse()));

		String name = "scopedInnerBean";

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		try {
			assertNull(request.getAttribute("scopedTarget." + name));
			assertEquals("scoped", bean.getSpouse().getName());
			assertNotNull(request.getAttribute("scopedTarget." + name));
			assertEquals(TestBean.class, request.getAttribute("scopedTarget." + name).getClass());
			assertEquals("scoped", ((TestBean) request.getAttribute("scopedTarget." + name)).getName());
		}
		finally {
			RequestContextHolder.setRequestAttributes(null);
		}
	}
