	@Test
	public void shouldSupportCustomFormat() throws Exception {
		resolver.setMessageCodeFormatter(new MessageCodeFormatter() {
			@Override
			public String format(String errorCode, String objectName, String field) {
				return DefaultMessageCodesResolver.Format.toDelimitedString(
						"CUSTOM-" + errorCode, objectName, field);
			}
		});
		String[] codes = resolver.resolveMessageCodes("errorCode", "objectName");
		assertThat(codes, is(equalTo(new String[] {
				"CUSTOM-errorCode.objectName",
				"CUSTOM-errorCode" })));
	}
