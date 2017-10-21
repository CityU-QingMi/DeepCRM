	@Test
	public void body() throws IOException {
		byte[] body = "Hello World".getBytes("UTF-8");
		this.builder.content(body);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		byte[] result = FileCopyUtils.copyToByteArray(request.getInputStream());

		assertArrayEquals(body, result);
	}
