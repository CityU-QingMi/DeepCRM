	@Test
	public void shouldSupportFieldPostfixFormat() throws Exception {
		resolver.setMessageCodeFormatter(Format.POSTFIX_ERROR_CODE);
		String[] codes = resolver.resolveMessageCodes("errorCode", "objectName", "field",
				TestBean.class);
		assertThat(codes, is(equalTo(new String[] {
				"objectName.field.errorCode",
				"field.errorCode",
				"org.springframework.tests.sample.beans.TestBean.errorCode",
				"errorCode" })));
	}
