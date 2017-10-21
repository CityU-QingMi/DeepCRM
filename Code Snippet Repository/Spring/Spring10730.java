	@Test
	public void withIncompleteInitialization() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		given(request.getCharacterEncoding()).willReturn(null);
		given(request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE)).willReturn(null);
		given(request.getAttribute(CharacterEncodingFilter.class.getName() + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX)).willReturn(null);

		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = mock(FilterChain.class);

		CharacterEncodingFilter filter = new CharacterEncodingFilter(ENCODING);
		filter.doFilter(request, response, filterChain);

		verify(request).setCharacterEncoding(ENCODING);
		verify(request).setAttribute(CharacterEncodingFilter.class.getName() + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX, Boolean.TRUE);
		verify(request).removeAttribute(CharacterEncodingFilter.class.getName() + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX);
		verify(filterChain).doFilter(request, response);
	}
