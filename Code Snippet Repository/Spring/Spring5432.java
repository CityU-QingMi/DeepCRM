	@Test
	public void updateMessageDigest() throws Exception {
		StringBuilder builder = new StringBuilder("\"0");
		this.os.write(this.helloBytes);
		InputStream inputStream = this.os.getInputStream();
		DigestUtils.appendMd5DigestAsHex(inputStream, builder);
		builder.append("\"");
		String actual = builder.toString();
		assertEquals("\"0b10a8db164e0754105b7a99be72e3fe5\"", actual);
	}
