	@Test
	public void retrieveAttributes() throws Exception {
		sessionAttributeStore.storeAttribute(request, "attr1", "value1");
		sessionAttributeStore.storeAttribute(request, "attr2", "value2");
		sessionAttributeStore.storeAttribute(request, "attr3", new TestBean());
		sessionAttributeStore.storeAttribute(request, "attr4", new TestBean());

		assertEquals("Named attributes (attr1, attr2) should be 'known' right away",
				new HashSet<>(asList("attr1", "attr2")),
				sessionAttributesHandler.retrieveAttributes(request).keySet());

		// Resolve 'attr3' by type
		sessionAttributesHandler.isHandlerSessionAttribute("attr3", TestBean.class);

		assertEquals("Named attributes (attr1, attr2) and resolved attribute (att3) should be 'known'",
				new HashSet<>(asList("attr1", "attr2", "attr3")),
				sessionAttributesHandler.retrieveAttributes(request).keySet());
	}
