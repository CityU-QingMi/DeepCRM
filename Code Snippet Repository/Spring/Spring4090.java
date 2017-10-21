	@Test
	public void shouldResolveIndexedFieldMessageCode() throws Exception {
		String[] codes = resolver.resolveMessageCodes("errorCode", "objectName", "a.b[3].c[5].d",
				TestBean.class);
		assertThat(codes, is(equalTo(new String[] {
				"errorCode.objectName.a.b[3].c[5].d",
				"errorCode.objectName.a.b[3].c.d",
				"errorCode.objectName.a.b.c.d",
				"errorCode.a.b[3].c[5].d",
				"errorCode.a.b[3].c.d",
				"errorCode.a.b.c.d",
				"errorCode.d",
				"errorCode.org.springframework.tests.sample.beans.TestBean",
				"errorCode" })));
	}
