	@Test
	public void multipartFilesAsStringArray() throws Exception {
		initServletWithControllers(MultipartController.class);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.setRequestURI("/stringArray");
		request.addFile(new MockMultipartFile("content", "Juergen".getBytes()));
		request.addFile(new MockMultipartFile("content", "Eva".getBytes()));
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Juergen-Eva", response.getContentAsString());
	}
