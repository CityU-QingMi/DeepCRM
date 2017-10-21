	@Test
	public void shouldHandleNotModifiedResponse() throws Exception {
		long currentTime = new Date().getTime();
		long oneMinuteAgo = currentTime - (1000 * 60);
		String etagValue = "\"deadb33f8badf00d\"";
		ResponseEntity<String> returnValue = ResponseEntity.status(HttpStatus.NOT_MODIFIED)
				.eTag(etagValue).lastModified(oneMinuteAgo).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.NOT_MODIFIED, null, etagValue, oneMinuteAgo);
	}
