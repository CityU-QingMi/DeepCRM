	@Test
	public void setContentAndGetContentAsStringWithExplicitCharacterEncoding() throws IOException {
		String palindrome = "ablE was I ere I saw Elba";
		byte[] bytes = palindrome.getBytes("UTF-16");
		request.setCharacterEncoding("UTF-16");
		request.setContent(bytes);
		assertEquals(bytes.length, request.getContentLength());
		assertNotNull(request.getContentAsString());
		assertEquals(palindrome, request.getContentAsString());
	}
