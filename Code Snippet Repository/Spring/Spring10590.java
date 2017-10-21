	@Test
	public void checkNotModifiedTimestampConditionalPut() throws Exception {
		long currentEpoch = currentDate.getTime();
		long oneMinuteAgo = currentEpoch - (1000 * 60);
		servletRequest.setMethod("PUT");
		servletRequest.addHeader("If-UnModified-Since", currentEpoch);

		assertFalse(request.checkNotModified(oneMinuteAgo));
		assertEquals(200, servletResponse.getStatus());
		assertEquals(null, servletResponse.getHeader("Last-Modified"));
	}
