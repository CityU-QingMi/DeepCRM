	@Test
	public void handleAndValidateRequestPart() throws Exception {
		MockMultipartHttpServletRequest multipartRequest = new MockMultipartHttpServletRequest();
		multipartRequest.addFile(new MockMultipartFile("requestPart", "", "text/plain", "content".getBytes("UTF-8")));

		HandlerMethod handlerMethod = handlerMethod("handleAndValidateRequestPart", String.class, Errors.class, Model.class);
		ModelAndView mav = handlerAdapter.handle(multipartRequest, response, handlerMethod);

		assertNotNull(mav);
		assertEquals(1, mav.getModelMap().get("error count"));
	}
