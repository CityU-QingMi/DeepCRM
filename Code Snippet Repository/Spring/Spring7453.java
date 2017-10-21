	@SuppressWarnings("")
	private void handleMessage(
			@Payload String param,
			@Payload(required=false) String paramNotRequired,
			@Payload(required=true) Locale nonConvertibleRequiredParam,
			@Payload("foo.bar") String paramWithSpelExpression,
			@MyValid @Payload String validParam,
			@Validated String validParamNotAnnotated,
			String paramNotAnnotated) {
	}
