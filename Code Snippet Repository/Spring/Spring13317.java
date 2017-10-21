	@Test
	public void flashAttributesWithQueryParamsWithSpace() throws Exception {

		String encodedValue = URLEncoder.encode("1 2", "UTF-8");

		FlashMap flashMap = new FlashMap();
		flashMap.put("key", "value");
		flashMap.setTargetRequestPath("/path");
		flashMap.addTargetRequestParam("param", encodedValue);

		this.request.setCharacterEncoding("UTF-8");
		this.flashMapManager.saveOutputFlashMap(flashMap, this.request, this.response);

		MockHttpServletRequest requestAfterRedirect = new MockHttpServletRequest("GET", "/path");
		requestAfterRedirect.setQueryString("param=" + encodedValue);
		requestAfterRedirect.addParameter("param", "1 2");

		flashMap = this.flashMapManager.retrieveAndUpdate(requestAfterRedirect, new MockHttpServletResponse());
		assertNotNull(flashMap);
		assertEquals(1, flashMap.size());
		assertEquals("value", flashMap.get("key"));
	}
