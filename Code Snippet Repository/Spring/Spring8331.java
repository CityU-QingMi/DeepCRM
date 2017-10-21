	public ResultMatcher dateValue(final String name, final long value) {
		return result -> {
			SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			String formatted = format.format(new Date(value));
			MockHttpServletResponse response = result.getResponse();
			assertTrue("Response does not contain header '" + name + "'", response.containsHeader(name));
			assertEquals("Response header '" + name + "'", formatted, response.getHeader(name));
		};
	}
