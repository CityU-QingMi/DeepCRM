	@Test
	public void checkNotModifiedTimestampConditionalPutConflict() throws Exception {
		long currentEpoch = currentDate.getTime();
		long oneMinuteAgo = currentEpoch - (1000 * 60);
		servletRequest.setMethod("PUT");
		servletRequest.addHeader("If-UnModified-Since", oneMinuteAgo);

		assertTrue(request.checkNotModified(currentEpoch));
		assertEquals(412, servletResponse.getStatus());
		assertEquals(null, servletResponse.getHeader("Last-Modified"));
	}
