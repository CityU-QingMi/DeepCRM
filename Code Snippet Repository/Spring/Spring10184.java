	@Test
	public void writeSubTypeList() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		List<MyBean> beans = new ArrayList<>();
		MyBean foo = new MyBean();
		foo.setString("Foo");
		foo.setNumber(42);
		beans.add(foo);
		MyBean bar = new MyBean();
		bar.setString("Bar");
		bar.setNumber(123);
		beans.add(bar);
		ParameterizedTypeReference<List<MyInterface>> typeReference =
				new ParameterizedTypeReference<List<MyInterface>>() {};

		this.converter.writeInternal(beans, typeReference.getType(), outputMessage);

		String result = outputMessage.getBodyAsString(StandardCharsets.UTF_8);
		assertTrue(result.contains("\"string\":\"Foo\""));
		assertTrue(result.contains("\"number\":42"));
		assertTrue(result.contains("\"string\":\"Bar\""));
		assertTrue(result.contains("\"number\":123"));
	}
