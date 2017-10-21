	@Test
	public void getBodyWithWrappedRequest() throws Exception {
		byte[] bytes = "content".getBytes("UTF-8");
		MultipartFile part = new MockMultipartFile("part", "", "application/json", bytes);
		this.mockRequest.addFile(part);
		HttpServletRequest wrapped = new HttpServletRequestWrapper(this.mockRequest);
		ServerHttpRequest request = new RequestPartServletServerHttpRequest(wrapped, "part");

		byte[] result = FileCopyUtils.copyToByteArray(request.getBody());
		assertArrayEquals(bytes, result);
	}
