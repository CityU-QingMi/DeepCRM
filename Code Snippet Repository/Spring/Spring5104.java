	@Test
	public void synthesizeAnnotationFromMapWithoutAttributeAliases() throws Exception {
		Component component = WebController.class.getAnnotation(Component.class);
		assertNotNull(component);

		Map<String, Object> map = Collections.singletonMap(VALUE, "webController");
		Component synthesizedComponent = synthesizeAnnotation(map, Component.class, WebController.class);
		assertNotNull(synthesizedComponent);

		assertNotSame(component, synthesizedComponent);
		assertEquals("value from component: ", "webController", component.value());
		assertEquals("value from synthesized component: ", "webController", synthesizedComponent.value());
	}
