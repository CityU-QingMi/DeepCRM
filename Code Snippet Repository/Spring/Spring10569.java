	@Test
	public void testGetFromScopeThroughDynamicProxy() throws Exception {
		String name = "requestScopedProxy";
		ITestBean bean = (ITestBean) this.beanFactory.getBean(name);
		// assertTrue(AopUtils.isJdkDynamicProxy(bean));

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		try {
			assertNull(request.getAttribute("scopedTarget." + name));
			assertEquals("scoped", bean.getName());
			assertNotNull(request.getAttribute("scopedTarget." + name));
			TestBean target = (TestBean) request.getAttribute("scopedTarget." + name);
			assertEquals(TestBean.class, target.getClass());
			assertEquals("scoped", target.getName());
			assertSame(bean, this.beanFactory.getBean(name));
			assertEquals(bean.toString(), target.toString());
		}
		finally {
			RequestContextHolder.setRequestAttributes(null);
		}
	}
