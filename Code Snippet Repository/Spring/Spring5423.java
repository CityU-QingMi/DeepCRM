	@Test
	public void md5Hex() throws IOException {
		String expected = "b10a8db164e0754105b7a99be72e3fe5";

		String hash = DigestUtils.md5DigestAsHex(bytes);
		assertEquals("Invalid hash", expected, hash);

		hash = DigestUtils.md5DigestAsHex(new ByteArrayInputStream(bytes));
		assertEquals("Invalid hash", expected, hash);
	}
