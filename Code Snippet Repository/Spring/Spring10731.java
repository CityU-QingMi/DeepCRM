	@Test
	public void setForceEncodingOnRequestOnly() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		request.setCharacterEncoding(ENCODING);
		given(request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE)).willReturn(null);
		given(request.getAttribute(FILTER_NAME + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX)).willReturn(null);

		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain filterChain = mock(FilterChain.class);

		CharacterEncodingFilter filter = new CharacterEncodingFilter(ENCODING, true, false);
		filter.init(new MockFilterConfig(FILTER_NAME));
		filter.doFilter(request, response, filterChain);

		verify(request).setAttribute(FILTER_NAME + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX, Boolean.TRUE);
		verify(request).removeAttribute(FILTER_NAME + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX);
		verify(request, times(2)).setCharacterEncoding(ENCODING);
		verify(response, never()).setCharacterEncoding(ENCODING);
		verify(filterChain).doFilter(request, response);
	}
