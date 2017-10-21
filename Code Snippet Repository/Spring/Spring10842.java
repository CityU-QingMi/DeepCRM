	@Test
	public void handleModelReturnValue() throws Exception {
		mavContainer.addAttribute("attr1", "value1");
		Model returnValue = new ExtendedModelMap();
		returnValue.addAttribute("attr2", "value2");

		processor.handleReturnValue(returnValue , returnParamModel, mavContainer, webRequest);

		assertEquals("value1", mavContainer.getModel().get("attr1"));
		assertEquals("value2", mavContainer.getModel().get("attr2"));
	}
