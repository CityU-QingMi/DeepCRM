	@Test
	public void testCanonicalPropertyName() {
		assertEquals("map", PropertyAccessorUtils.canonicalPropertyName("map"));
		assertEquals("map[key1]", PropertyAccessorUtils.canonicalPropertyName("map[key1]"));
		assertEquals("map[key1]", PropertyAccessorUtils.canonicalPropertyName("map['key1']"));
		assertEquals("map[key1]", PropertyAccessorUtils.canonicalPropertyName("map[\"key1\"]"));
		assertEquals("map[key1][key2]", PropertyAccessorUtils.canonicalPropertyName("map[key1][key2]"));
		assertEquals("map[key1][key2]", PropertyAccessorUtils.canonicalPropertyName("map['key1'][\"key2\"]"));
		assertEquals("map[key1].name", PropertyAccessorUtils.canonicalPropertyName("map[key1].name"));
		assertEquals("map[key1].name", PropertyAccessorUtils.canonicalPropertyName("map['key1'].name"));
		assertEquals("map[key1].name", PropertyAccessorUtils.canonicalPropertyName("map[\"key1\"].name"));
	}
