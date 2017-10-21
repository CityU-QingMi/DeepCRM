	@Test
	public void retrieveAndUpdateMatchByOriginatingPathAndQueryString() {
		FlashMap flashMap = new FlashMap();
		flashMap.put("key", "value");
		flashMap.setTargetRequestPath("/accounts");
		flashMap.addTargetRequestParam("a", "b");

		this.flashMapManager.setFlashMaps(Collections.singletonList(flashMap));

		this.request.setAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE, "/accounts");
		this.request.setAttribute(WebUtils.FORWARD_QUERY_STRING_ATTRIBUTE, "a=b");
		this.request.setRequestURI("/mvc/accounts");
		this.request.setQueryString("x=y");
		FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(this.request, this.response);

		assertEquals(flashMap, inputFlashMap);
		assertEquals("Input FlashMap should have been removed", 0, this.flashMapManager.getFlashMaps().size());
	}
