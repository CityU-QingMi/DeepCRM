	@SuppressWarnings("")
	@Test
	public void flashMap() throws Exception {
		RedirectView rv = new RedirectView();
		rv.setUrl("http://url.somewhere.com/path");
		rv.setHttp10Compatible(false);
		FlashMap flashMap = new FlashMap();
		flashMap.put("successMessage", "yay!");
		request.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE, flashMap);
		ModelMap model = new ModelMap("id", "1");
		rv.render(model, request, response);
		assertEquals(303, response.getStatus());
		assertEquals("http://url.somewhere.com/path?id=1", response.getHeader("Location"));

		assertEquals("/path", flashMap.getTargetRequestPath());
		assertEquals(model, flashMap.getTargetRequestParams().toSingleValueMap());
	}
