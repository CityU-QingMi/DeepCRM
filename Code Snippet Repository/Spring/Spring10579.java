	private void doSkipImmutableValue(Object immutableValue) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		given(request.getSession(anyBoolean())).willReturn(session);
		given(session.getAttribute(KEY)).willReturn(immutableValue);

		ServletRequestAttributes attrs = new ServletRequestAttributes(request);
		attrs.getAttribute(KEY, RequestAttributes.SCOPE_SESSION);
		attrs.requestCompleted();

		verify(session, times(2)).getAttribute(KEY);
		verifyNoMoreInteractions(session);
	}
