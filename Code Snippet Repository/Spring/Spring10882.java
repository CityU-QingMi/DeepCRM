	@Test
	public void cleanupAttributes() throws Exception {
		sessionAttributeStore.storeAttribute(request, "attr1", "value1");
		sessionAttributeStore.storeAttribute(request, "attr2", "value2");
		sessionAttributeStore.storeAttribute(request, "attr3", new TestBean());

		sessionAttributesHandler.cleanupAttributes(request);

		assertNull(sessionAttributeStore.retrieveAttribute(request, "attr1"));
		assertNull(sessionAttributeStore.retrieveAttribute(request, "attr2"));
		assertNotNull(sessionAttributeStore.retrieveAttribute(request, "attr3"));

		// Resolve 'attr3' by type
		sessionAttributesHandler.isHandlerSessionAttribute("attr3", TestBean.class);
		sessionAttributesHandler.cleanupAttributes(request);

		assertNull(sessionAttributeStore.retrieveAttribute(request, "attr3"));
	}
