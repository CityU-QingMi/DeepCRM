	@Test
	public void sessionAttribute() throws Exception {
		this.attributeStore.storeAttribute(this.webRequest, "sessionAttr", "sessionAttrValue");

		ModelFactory modelFactory = createModelFactory("modelAttr", Model.class);
		HandlerMethod handlerMethod = createHandlerMethod("handle");
		modelFactory.initModel(this.webRequest, this.mavContainer, handlerMethod);

		assertEquals("sessionAttrValue", this.mavContainer.getModel().get("sessionAttr"));
	}
