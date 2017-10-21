	@Test
	public void wrapPutAndPatchOnly() throws Exception {
		request.setContent("foo=bar".getBytes("ISO-8859-1"));
		for (HttpMethod method : HttpMethod.values()) {
			request.setMethod(method.name());
			filterChain = new MockFilterChain();
			filter.doFilter(request, response, filterChain);
			if (method.equals(HttpMethod.PUT) || method.equals(HttpMethod.PATCH)) {
				assertNotSame("Should wrap HTTP method " + method, request, filterChain.getRequest());
			}
			else {
				assertSame("Should not wrap for HTTP method " + method, request, filterChain.getRequest());
			}
		}
	}
