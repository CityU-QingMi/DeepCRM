	@Test
	public void write() throws IOException {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		MyBean body = new MyBean();
		body.setString("Foo");
		body.setNumber(42);
		body.setFraction(42F);
		body.setArray(new String[]{"Foo", "Bar"});
		body.setBool(true);
		body.setBytes(new byte[]{0x1, 0x2});
		converter.write(body, null, outputMessage);
		assertArrayEquals(mapper.writeValueAsBytes(body), outputMessage.getBodyAsBytes());
		assertEquals("Invalid content-type", new MediaType("application", "x-jackson-smile", StandardCharsets.UTF_8),
				outputMessage.getHeaders().getContentType());
	}
