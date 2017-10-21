	@Test
	public void sessionAttributeNotPresent() throws Exception {
		ModelFactory modelFactory = new ModelFactory(null, null, this.attributeHandler);
		HandlerMethod handlerMethod = createHandlerMethod("handleSessionAttr", String.class);
		try {
			modelFactory.initModel(this.webRequest, this.mavContainer, handlerMethod);
			fail("Expected HttpSessionRequiredException");
		}
		catch (HttpSessionRequiredException e) {
			// expected
		}

		// Now add attribute and try again
		this.attributeStore.storeAttribute(this.webRequest, "sessionAttr", "sessionAttrValue");

		modelFactory.initModel(this.webRequest, this.mavContainer, handlerMethod);
		assertEquals("sessionAttrValue", this.mavContainer.getModel().get("sessionAttr"));
	}
