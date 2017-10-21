	@Test
	public void shouldHandleETagAndLastModifiedWithHttp304() throws Exception {
		long currentTime = new Date().getTime();
		long oneMinuteAgo = currentTime - (1000 * 60);
		String etagValue = "\"deadb33f8badf00d\"";
		servletRequest.addHeader(HttpHeaders.IF_MODIFIED_SINCE, RFC_1123_DATE_TIME.format(ofEpochMilli(currentTime).atZone(GMT)));
		servletRequest.addHeader(HttpHeaders.IF_NONE_MATCH, etagValue);
		ResponseEntity<String> returnValue = ResponseEntity.ok().eTag(etagValue).lastModified(oneMinuteAgo).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.NOT_MODIFIED, null, etagValue, oneMinuteAgo);
	}
