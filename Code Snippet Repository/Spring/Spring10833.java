	@Test
	public void modelAttributeFromSessionWithBindingDisabled() throws Exception {
		Foo foo = new Foo();
		this.attributeStore.storeAttribute(this.webRequest, "foo", foo);

		ModelFactory modelFactory = createModelFactory("modelAttrWithBindingDisabled");
		HandlerMethod handlerMethod = createHandlerMethod("handle");
		modelFactory.initModel(this.webRequest, this.mavContainer, handlerMethod);

		assertTrue(this.mavContainer.containsAttribute("foo"));
		assertSame(foo, this.mavContainer.getModel().get("foo"));
		assertTrue(this.mavContainer.isBindingDisabled("foo"));
	}
