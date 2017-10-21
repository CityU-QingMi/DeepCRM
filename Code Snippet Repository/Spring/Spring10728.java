	@Test
	public void doesNotIfEncodingIsNotEmptyAndNotForced() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		given(request.getCharacterEncoding()).willReturn(ENCODING);
		given(request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE)).willReturn(null);
		given(request.getAttribute(FILTER_NAME + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX)).willReturn(null);

		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = mock(FilterChain.class);

		CharacterEncodingFilter filter = new CharacterEncodingFilter(ENCODING);
		filter.init(new MockFilterConfig(FILTER_NAME));
		filter.doFilter(request, response, filterChain);

		verify(request).setAttribute(FILTER_NAME + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX, Boolean.TRUE);
		verify(request).removeAttribute(FILTER_NAME + OncePerRequestFilter.ALREADY_FILTERED_SUFFIX);
		verify(filterChain).doFilter(request, response);
	}
