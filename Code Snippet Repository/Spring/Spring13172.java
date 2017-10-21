	@Test
	public void invokeAndHandle_VoidRequestNotModified() throws Exception {
		this.request.addHeader("If-Modified-Since", 10 * 1000 * 1000);
		int lastModifiedTimestamp = 1000 * 1000;
		this.webRequest.checkNotModified(lastModifiedTimestamp);

		ServletInvocableHandlerMethod handlerMethod = getHandlerMethod(new Handler(), "notModified");
		handlerMethod.invokeAndHandle(this.webRequest, this.mavContainer);

		assertTrue("Null return value + 'not modified' request should result in 'request handled'",
				this.mavContainer.isRequestHandled());
	}
