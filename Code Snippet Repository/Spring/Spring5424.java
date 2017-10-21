	@Test
	public void md5StringBuilder() throws IOException {
		String expected = "b10a8db164e0754105b7a99be72e3fe5";

		StringBuilder builder = new StringBuilder();
		DigestUtils.appendMd5DigestAsHex(bytes, builder);
		assertEquals("Invalid hash", expected, builder.toString());

		builder = new StringBuilder();
		DigestUtils.appendMd5DigestAsHex(new ByteArrayInputStream(bytes), builder);
		assertEquals("Invalid hash", expected, builder.toString());
	}
