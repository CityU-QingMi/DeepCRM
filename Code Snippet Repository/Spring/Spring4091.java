	@Test
	public void shouldResolveFieldMessageCodeWithPrefix() throws Exception {
		resolver.setPrefix("prefix.");
		String[] codes = resolver.resolveMessageCodes("errorCode", "objectName", "field",
				TestBean.class);
		assertThat(codes, is(equalTo(new String[] {
				"prefix.errorCode.objectName.field",
				"prefix.errorCode.field",
				"prefix.errorCode.org.springframework.tests.sample.beans.TestBean",
				"prefix.errorCode" })));
	}
