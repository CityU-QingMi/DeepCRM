	@Test
	public void encodeToStringWithJdk8VsJaxb() throws UnsupportedEncodingException {
		byte[] bytes = new byte[]
				{-0x4f, 0xa, -0x73, -0x4f, 0x64, -0x20, 0x75, 0x41, 0x5, -0x49, -0x57, -0x65, -0x19, 0x2e, 0x3f, -0x1b};
		assertEquals(Base64Utils.encodeToString(bytes), DatatypeConverter.printBase64Binary(bytes));
		assertArrayEquals(bytes, Base64Utils.decodeFromString(Base64Utils.encodeToString(bytes)));
		assertArrayEquals(bytes, DatatypeConverter.parseBase64Binary(DatatypeConverter.printBase64Binary(bytes)));

		bytes = "Hello World".getBytes("UTF-8");
		assertEquals(Base64Utils.encodeToString(bytes), DatatypeConverter.printBase64Binary(bytes));
		assertArrayEquals(bytes, Base64Utils.decodeFromString(Base64Utils.encodeToString(bytes)));
		assertArrayEquals(bytes, DatatypeConverter.parseBase64Binary(DatatypeConverter.printBase64Binary(bytes)));

		bytes = "Hello World\r\nSecond Line".getBytes("UTF-8");
		assertEquals(Base64Utils.encodeToString(bytes), DatatypeConverter.printBase64Binary(bytes));
		assertArrayEquals(bytes, Base64Utils.decodeFromString(Base64Utils.encodeToString(bytes)));
		assertArrayEquals(bytes, DatatypeConverter.parseBase64Binary(DatatypeConverter.printBase64Binary(bytes)));

		bytes = "Hello World\r\nSecond Line\r\n".getBytes("UTF-8");
		assertEquals(Base64Utils.encodeToString(bytes), DatatypeConverter.printBase64Binary(bytes));
		assertArrayEquals(bytes, Base64Utils.decodeFromString(Base64Utils.encodeToString(bytes)));
		assertArrayEquals(bytes, DatatypeConverter.parseBase64Binary(DatatypeConverter.printBase64Binary(bytes)));
	}
