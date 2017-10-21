	private void testBindFoo(String modelKey, MethodParameter param, Function<Object, Foo> valueExtractor)
			throws Exception {

		Object value = createResolver()
				.resolveArgument(param, this.bindContext, postForm("name=Robert&age=25"))
				.block(Duration.ZERO);

		Foo foo = valueExtractor.apply(value);
		assertEquals("Robert", foo.getName());
		assertEquals(25, foo.getAge());

		String bindingResultKey = BindingResult.MODEL_KEY_PREFIX + modelKey;

		Map<String, Object> map = bindContext.getModel().asMap();
		assertEquals(map.toString(), 2, map.size());
		assertSame(foo, map.get(modelKey));
		assertNotNull(map.get(bindingResultKey));
		assertTrue(map.get(bindingResultKey) instanceof BindingResult);
	}
