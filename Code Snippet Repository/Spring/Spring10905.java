	@Test
	public void getBody() throws Exception {
		byte[] bytes = "content".getBytes("UTF-8");
		MultipartFile part = new MockMultipartFile("part", "", "application/json", bytes);
		this.mockRequest.addFile(part);
		ServerHttpRequest request = new RequestPartServletServerHttpRequest(this.mockRequest, "part");

		byte[] result = FileCopyUtils.copyToByteArray(request.getBody());
		assertArrayEquals(bytes, result);
	}
