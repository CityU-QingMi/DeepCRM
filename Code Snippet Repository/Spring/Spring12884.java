	@Test
	public void shouldHandleValidatorHeadersInPutResponses() throws Exception {
		servletRequest.setMethod("PUT");
		String etagValue = "\"some-etag\"";
		ResponseEntity<String> returnValue = ResponseEntity.ok().header(HttpHeaders.ETAG, etagValue).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.OK, "body", etagValue, -1);
	}
