	@Test
	public void shouldHandleIfNoneMatchIfMatch() throws Exception {
		String etagValue = "\"some-etag\"";
		servletRequest.addHeader(HttpHeaders.IF_NONE_MATCH, etagValue);
		servletRequest.addHeader(HttpHeaders.IF_MATCH, "ifmatch");
		ResponseEntity<String> returnValue = ResponseEntity.ok().eTag(etagValue).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.NOT_MODIFIED, null, etagValue, -1);
	}
