	private void testBindBar(MethodParameter param) throws Exception {
		Object value = createResolver()
				.resolveArgument(param, this.bindContext, postForm("name=Robert&age=25&count=1"))
				.block(Duration.ZERO);

		Bar bar = (Bar) value;
		assertEquals("Robert", bar.getName());
		assertEquals(25, bar.getAge());
		assertEquals(1, bar.getCount());

		String key = "bar";
		String bindingResultKey = BindingResult.MODEL_KEY_PREFIX + key;

		Map<String, Object> map = bindContext.getModel().asMap();
		assertEquals(map.toString(), 2, map.size());
		assertSame(bar, map.get(key));
		assertNotNull(map.get(bindingResultKey));
		assertTrue(map.get(bindingResultKey) instanceof BindingResult);
	}
