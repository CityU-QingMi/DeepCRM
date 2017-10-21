	@Test
	public void handleRequestPart() throws Exception {
		MockMultipartHttpServletRequest multipartRequest = new MockMultipartHttpServletRequest();
		multipartRequest.addFile(new MockMultipartFile("requestPart", "", "text/plain", "content".getBytes("UTF-8")));

		HandlerMethod handlerMethod = handlerMethod("handleRequestPart", String.class, Model.class);
		ModelAndView mav = handlerAdapter.handle(multipartRequest, response, handlerMethod);

		assertNotNull(mav);
		assertEquals("content", mav.getModelMap().get("requestPart"));
	}
