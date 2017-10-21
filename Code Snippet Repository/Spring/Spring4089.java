	@Test
	public void shouldResolveFieldMessageCode() throws Exception {
		String[] codes = resolver.resolveMessageCodes("errorCode", "objectName", "field",
				TestBean.class);
		assertThat(codes, is(equalTo(new String[] {
				"errorCode.objectName.field",
				"errorCode.field",
				"errorCode.org.springframework.tests.sample.beans.TestBean",
				"errorCode" })));
	}
