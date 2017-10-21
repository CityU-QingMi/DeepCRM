	@Test
	public void shouldHandleLastModifiedWithHttp304() throws Exception {
		long currentTime = new Date().getTime();
		long oneMinuteAgo = currentTime - (1000 * 60);
		servletRequest.addHeader(HttpHeaders.IF_MODIFIED_SINCE, RFC_1123_DATE_TIME.format(ofEpochMilli(currentTime).atZone(GMT)));
		ResponseEntity<String> returnValue = ResponseEntity.ok().lastModified(oneMinuteAgo).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.NOT_MODIFIED, null, null, oneMinuteAgo);
	}
