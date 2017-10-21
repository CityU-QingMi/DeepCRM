	@Test
	public void flashMap() throws Exception {
		FlashMap flashMap = new FlashMap();
		flashMap.put("attrName", "attrValue");
		this.request.setAttribute(DispatcherServlet.class.getName() + ".OUTPUT_FLASH_MAP", flashMap);

		this.handler.handle(this.mvcResult);

		assertValue("FlashMap", "Attribute", "attrName");
		assertValue("FlashMap", "value", "attrValue");
	}
