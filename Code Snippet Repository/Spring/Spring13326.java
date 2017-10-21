	@Test
	public void saveOutputFlashMapDecodeTargetPath() throws InterruptedException {
		FlashMap flashMap = new FlashMap();
		flashMap.put("key", "value");

		flashMap.setTargetRequestPath("/once%20upon%20a%20time");
		this.flashMapManager.saveOutputFlashMap(flashMap, this.request, this.response);

		assertEquals("/once upon a time", flashMap.getTargetRequestPath());
	}
