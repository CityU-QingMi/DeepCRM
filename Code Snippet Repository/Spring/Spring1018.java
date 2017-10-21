	@Test
	public void propertyTypeIndexedProperty() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		assertEquals(null, accessor.getPropertyType("map[key0]"));

		accessor = createAccessor(target);
		accessor.setPropertyValue("map[key0]", "my String");
		assertEquals(String.class, accessor.getPropertyType("map[key0]"));

		accessor = createAccessor(target);
		accessor.registerCustomEditor(String.class, "map[key0]", new StringTrimmerEditor(false));
		assertEquals(String.class, accessor.getPropertyType("map[key0]"));
	}
