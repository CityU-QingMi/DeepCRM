	@Test
	public void retrieveAndUpdateMatchWithTrailingSlash() {
		FlashMap flashMap = new FlashMap();
		flashMap.put("key", "value");
		flashMap.setTargetRequestPath("/path");

		this.flashMapManager.setFlashMaps(Arrays.asList(flashMap));

		this.request.setRequestURI("/path/");
		FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(this.request, this.response);

		assertEquals(flashMap, inputFlashMap);
		assertEquals("Input FlashMap should have been removed", 0, this.flashMapManager.getFlashMaps().size());
	}
