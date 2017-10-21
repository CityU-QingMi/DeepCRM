	@Test
	public void updateAccessedAttributes() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		given(request.getSession(anyBoolean())).willReturn(session);
		given(session.getAttribute(KEY)).willReturn(VALUE);

		ServletRequestAttributes attrs = new ServletRequestAttributes(request);
		assertSame(VALUE, attrs.getAttribute(KEY, RequestAttributes.SCOPE_SESSION));
		attrs.requestCompleted();

		verify(session, times(2)).getAttribute(KEY);
		verify(session).setAttribute(KEY, VALUE);
		verifyNoMoreInteractions(session);
	}
