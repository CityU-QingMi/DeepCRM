	@Test
	public void setSessionScopedAttributeAfterCompletion() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(KEY, VALUE);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		ServletRequestAttributes attrs = new ServletRequestAttributes(request);
		assertSame(VALUE, attrs.getAttribute(KEY, RequestAttributes.SCOPE_SESSION));
		attrs.requestCompleted();
		request.close();
		attrs.setAttribute(KEY, VALUE, RequestAttributes.SCOPE_SESSION);
		assertSame(VALUE, session.getAttribute(KEY));
	}
