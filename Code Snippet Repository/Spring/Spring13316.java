	@Test
	public void saveOutputFlashMapDecodeParameters() throws Exception {

		FlashMap flashMap = new FlashMap();
		flashMap.put("key", "value");
		flashMap.setTargetRequestPath("/path");
		flashMap.addTargetRequestParam("param", "%D0%90%D0%90");
		flashMap.addTargetRequestParam("param", "%D0%91%D0%91");
		flashMap.addTargetRequestParam("param", "%D0%92%D0%92");
		flashMap.addTargetRequestParam("%3A%2F%3F%23%5B%5D%40", "value");

		this.request.setCharacterEncoding("UTF-8");
		this.flashMapManager.saveOutputFlashMap(flashMap, this.request, this.response);

		MockHttpServletRequest requestAfterRedirect = new MockHttpServletRequest("GET", "/path");
		requestAfterRedirect.setQueryString("param=%D0%90%D0%90&param=%D0%91%D0%91&param=%D0%92%D0%92&%3A%2F%3F%23%5B%5D%40=value");
		requestAfterRedirect.addParameter("param", "\u0410\u0410");
		requestAfterRedirect.addParameter("param", "\u0411\u0411");
		requestAfterRedirect.addParameter("param", "\u0412\u0412");
		requestAfterRedirect.addParameter(":/?#[]@", "value");

		flashMap = this.flashMapManager.retrieveAndUpdate(requestAfterRedirect, new MockHttpServletResponse());
		assertNotNull(flashMap);
		assertEquals(1, flashMap.size());
		assertEquals("value", flashMap.get("key"));
	}
