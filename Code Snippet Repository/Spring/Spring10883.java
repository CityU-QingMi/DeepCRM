	@Test
	public void storeAttributes() throws Exception {
		ModelMap model = new ModelMap();
		model.put("attr1", "value1");
		model.put("attr2", "value2");
		model.put("attr3", new TestBean());

		sessionAttributesHandler.storeAttributes(request, model);

		assertEquals("value1", sessionAttributeStore.retrieveAttribute(request, "attr1"));
		assertEquals("value2", sessionAttributeStore.retrieveAttribute(request, "attr2"));
		assertTrue(sessionAttributeStore.retrieveAttribute(request, "attr3") instanceof TestBean);
	}
