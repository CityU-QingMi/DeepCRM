	@Test
	public void checkNotModifiedHeaderAlreadySet() {
		long epochTime = currentDate.getTime();
		servletRequest.addHeader("If-Modified-Since", epochTime);
		servletResponse.addHeader("Last-Modified", CURRENT_TIME);

		assertTrue(request.checkNotModified(epochTime));
		assertEquals(304, servletResponse.getStatus());
		assertEquals(1, servletResponse.getHeaders("Last-Modified").size());
		assertEquals(CURRENT_TIME, servletResponse.getHeader("Last-Modified"));
	}
