	@Test
	public void registerMapping() throws Exception {
		String key1 = "/foo";
		String key2 = "/foo*";
		this.mapping.registerMapping(key1, this.handler, this.method1);
		this.mapping.registerMapping(key2, this.handler, this.method2);

		assertThat(this.mapping.getMappingRegistry().getMappings().keySet(),
				Matchers.contains(key1, key2));
	}
