	@Test
	public void modelAttributes() throws Exception {
		Foo foo = new Foo();
		Bar bar = new Bar();
		Rendering rendering = Rendering.view("foo").modelAttributes(foo, bar).build();

		Map<String, Object> map = new LinkedHashMap<>(2);
		map.put("foo", foo);
		map.put("bar", bar);
		assertEquals(map, rendering.modelAttributes());
	}
