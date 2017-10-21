	@Test
	public void testDestructionAtRequestCompletion() throws Exception {
		String name = "requestScopedDisposableObject";
		DerivedTestBean bean = (DerivedTestBean) this.beanFactory.getBean(name);
		assertTrue(AopUtils.isCglibProxy(bean));

		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		try {
			assertNull(request.getAttribute("scopedTarget." + name));
			assertEquals("scoped", bean.getName());
			assertNotNull(request.getAttribute("scopedTarget." + name));
			assertEquals(DerivedTestBean.class, request.getAttribute("scopedTarget." + name).getClass());
			assertEquals("scoped", ((TestBean) request.getAttribute("scopedTarget." + name)).getName());
			assertSame(bean, this.beanFactory.getBean(name));

			requestAttributes.requestCompleted();
			assertTrue(((TestBean) request.getAttribute("scopedTarget." + name)).wasDestroyed());
		}
		finally {
			RequestContextHolder.setRequestAttributes(null);
		}
	}
