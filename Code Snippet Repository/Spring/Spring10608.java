	@Test
	public void testRequestScope() {
		WebApplicationContext ac = initApplicationContext(WebApplicationContext.SCOPE_REQUEST);
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);
		try {
			assertNull(request.getAttribute(NAME));
			DerivedTestBean bean = ac.getBean(NAME, DerivedTestBean.class);
			assertSame(bean, request.getAttribute(NAME));
			assertSame(bean, ac.getBean(NAME));
			requestAttributes.requestCompleted();
			assertTrue(bean.wasDestroyed());
		}
		finally {
			RequestContextHolder.setRequestAttributes(null);
		}
	}
