	public ResultMatcher longValue(final String name, final long value) {
		return result -> {
			MockHttpServletResponse response = result.getResponse();
			assertTrue("Response does not contain header '" + name + "'", response.containsHeader(name));
			String headerValue = response.getHeader(name);
			if (headerValue != null) {
				assertEquals("Response header '" + name + "'", value, Long.parseLong(headerValue));
			}
		};
	}
