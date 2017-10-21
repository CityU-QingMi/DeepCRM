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
		String result = outputMessage.getBodyAsString(StandardCharsets.UTF_8);
		assertTrue(result.contains("<string>Foo</string>"));
		assertTrue(result.contains("<number>42</number>"));
		assertTrue(result.contains("<fraction>42.0</fraction>"));
		assertTrue(result.contains("<array><array>Foo</array><array>Bar</array></array>"));
		assertTrue(result.contains("<bool>true</bool>"));
		assertTrue(result.contains("<bytes>AQI=</bytes>"));
		assertEquals("Invalid content-type", new MediaType("application", "xml", StandardCharsets.UTF_8),
				outputMessage.getHeaders().getContentType());
	}
