	@Test
	public void shouldHandleInvalidIfNoneMatchWithHttp200() throws Exception {
		String etagValue = "\"deadb33f8badf00d\"";
		servletRequest.addHeader(HttpHeaders.IF_NONE_MATCH, "unquoted");
		ResponseEntity<String> returnValue = ResponseEntity.ok().eTag(etagValue).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.OK, "body", etagValue, -1);
	}
