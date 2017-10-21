	@Test
	public void checkNotModifiedNon2xxStatus() {
		long epochTime = currentDate.getTime();
		servletRequest.addHeader("If-Modified-Since", epochTime);
		servletResponse.setStatus(304);

		assertFalse(request.checkNotModified(epochTime));
		assertEquals(304, servletResponse.getStatus());
		assertNull(servletResponse.getHeader("Last-Modified"));
	}
