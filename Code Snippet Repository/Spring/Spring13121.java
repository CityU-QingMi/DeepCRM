	@Test
	public void multipartFileAsStringArray() throws Exception {
		initServletWithControllers(MultipartController.class);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.setRequestURI("/stringArray");
		request.addFile(new MockMultipartFile("content", "Juergen".getBytes()));
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Juergen", response.getContentAsString());
	}
