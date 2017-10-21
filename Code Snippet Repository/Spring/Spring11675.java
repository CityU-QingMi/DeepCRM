	@Test
	public void registerMappingWithSameMethodAndTwoHandlerInstances() throws Exception {
		String key1 = "foo";
		String key2 = "bar";
		MyHandler handler1 = new MyHandler();
		MyHandler handler2 = new MyHandler();
		this.mapping.registerMapping(key1, handler1, this.method1);
		this.mapping.registerMapping(key2, handler2, this.method1);

		assertThat(this.mapping.getMappingRegistry().getMappings().keySet(), Matchers.contains(key1, key2));
	}
