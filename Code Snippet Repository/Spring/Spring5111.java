	@Test
	public void synthesizeAnnotationFromAnnotationAttributesWithoutAttributeAliases() throws Exception {
		// 1) Get an annotation
		Component component = WebController.class.getAnnotation(Component.class);
		assertNotNull(component);

		// 2) Convert the annotation into AnnotationAttributes
		AnnotationAttributes attributes = getAnnotationAttributes(WebController.class, component);
		assertNotNull(attributes);

		// 3) Synthesize the AnnotationAttributes back into an annotation
		Component synthesizedComponent = synthesizeAnnotation(attributes, Component.class, WebController.class);
		assertNotNull(synthesizedComponent);

		// 4) Verify that the original and synthesized annotations are equivalent
		assertNotSame(component, synthesizedComponent);
		assertEquals(component, synthesizedComponent);
		assertEquals("value from component: ", "webController", component.value());
		assertEquals("value from synthesized component: ", "webController", synthesizedComponent.value());
	}
