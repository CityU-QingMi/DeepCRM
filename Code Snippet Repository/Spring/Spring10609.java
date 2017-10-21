	@Test
	public void testSessionScope() {
		WebApplicationContext ac = initApplicationContext(WebApplicationContext.SCOPE_SESSION);
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);
		try {
			assertNull(request.getSession().getAttribute(NAME));
			DerivedTestBean bean = ac.getBean(NAME, DerivedTestBean.class);
			assertSame(bean, request.getSession().getAttribute(NAME));
			assertSame(bean, ac.getBean(NAME));
			request.getSession().invalidate();
			assertTrue(bean.wasDestroyed());
		}
		finally {
			RequestContextHolder.setRequestAttributes(null);
		}
	}
