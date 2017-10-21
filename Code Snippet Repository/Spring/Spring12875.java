	@Test
	public void shouldHandleChangedETagAndLastModified() throws Exception {
		long currentTime = new Date().getTime();
		long oneMinuteAgo = currentTime - (1000 * 60);
		String etagValue = "\"deadb33f8badf00d\"";
		String changedEtagValue = "\"changed-etag-value\"";
		servletRequest.addHeader(HttpHeaders.IF_MODIFIED_SINCE, RFC_1123_DATE_TIME.format(ofEpochMilli(currentTime).atZone(GMT)));
		servletRequest.addHeader(HttpHeaders.IF_NONE_MATCH, etagValue);
		ResponseEntity<String> returnValue = ResponseEntity.ok()
				.eTag(changedEtagValue).lastModified(oneMinuteAgo).body("body");

		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertConditionalResponse(HttpStatus.OK, null, changedEtagValue, oneMinuteAgo);
	}
