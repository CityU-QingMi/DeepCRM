	@Test
	public void isReadableWritableForIndexedProperties() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);

		assertTrue(accessor.isReadableProperty("array"));
		assertTrue(accessor.isReadableProperty("list"));
		assertTrue(accessor.isReadableProperty("set"));
		assertTrue(accessor.isReadableProperty("map"));
		assertFalse(accessor.isReadableProperty("xxx"));

		assertTrue(accessor.isWritableProperty("array"));
		assertTrue(accessor.isWritableProperty("list"));
		assertTrue(accessor.isWritableProperty("set"));
		assertTrue(accessor.isWritableProperty("map"));
		assertFalse(accessor.isWritableProperty("xxx"));

		assertTrue(accessor.isReadableProperty("array[0]"));
		assertTrue(accessor.isReadableProperty("array[0].name"));
		assertTrue(accessor.isReadableProperty("list[0]"));
		assertTrue(accessor.isReadableProperty("list[0].name"));
		assertTrue(accessor.isReadableProperty("set[0]"));
		assertTrue(accessor.isReadableProperty("set[0].name"));
		assertTrue(accessor.isReadableProperty("map[key1]"));
		assertTrue(accessor.isReadableProperty("map[key1].name"));
		assertTrue(accessor.isReadableProperty("map[key4][0]"));
		assertTrue(accessor.isReadableProperty("map[key4][0].name"));
		assertTrue(accessor.isReadableProperty("map[key4][1]"));
		assertTrue(accessor.isReadableProperty("map[key4][1].name"));
		assertFalse(accessor.isReadableProperty("array[key1]"));

		assertTrue(accessor.isWritableProperty("array[0]"));
		assertTrue(accessor.isWritableProperty("array[0].name"));
		assertTrue(accessor.isWritableProperty("list[0]"));
		assertTrue(accessor.isWritableProperty("list[0].name"));
		assertTrue(accessor.isWritableProperty("set[0]"));
		assertTrue(accessor.isWritableProperty("set[0].name"));
		assertTrue(accessor.isWritableProperty("map[key1]"));
		assertTrue(accessor.isWritableProperty("map[key1].name"));
		assertTrue(accessor.isWritableProperty("map[key4][0]"));
		assertTrue(accessor.isWritableProperty("map[key4][0].name"));
		assertTrue(accessor.isWritableProperty("map[key4][1]"));
		assertTrue(accessor.isWritableProperty("map[key4][1].name"));
		assertFalse(accessor.isWritableProperty("array[key1]"));
	}
