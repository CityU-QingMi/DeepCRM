	@Test
	public void updateMessageDigestManyBuffers() throws Exception {
		StringBuilder builder = new StringBuilder("\"0");
		// filling at least one 256 buffer
		for ( int i = 0; i < 30; i++) {
			this.os.write(this.helloBytes);
		}
		InputStream inputStream = this.os.getInputStream();
		DigestUtils.appendMd5DigestAsHex(inputStream, builder);
		builder.append("\"");
		String actual = builder.toString();
		assertEquals("\"06225ca1e4533354c516e74512065331d\"", actual);
	}
