	@Test
	public void getContentType() throws Exception {
		MultipartFile part = new MockMultipartFile("part", "", "application/json", "content".getBytes("UTF-8"));
		this.mockRequest.addFile(part);
		ServerHttpRequest request = new RequestPartServletServerHttpRequest(this.mockRequest, "part");

		HttpHeaders headers = request.getHeaders();
		assertNotNull(headers);
		assertEquals(MediaType.APPLICATION_JSON, headers.getContentType());
	}
