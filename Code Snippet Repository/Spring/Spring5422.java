	@Test
	public void md5() throws IOException {
		byte[] expected = new byte[]
				{-0x4f, 0xa, -0x73, -0x4f, 0x64, -0x20, 0x75, 0x41, 0x5, -0x49, -0x57, -0x65, -0x19, 0x2e, 0x3f, -0x1b};

		byte[] result = DigestUtils.md5Digest(bytes);
		assertArrayEquals("Invalid hash", expected, result);

		result = DigestUtils.md5Digest(new ByteArrayInputStream(bytes));
		assertArrayEquals("Invalid hash", expected, result);
	}
