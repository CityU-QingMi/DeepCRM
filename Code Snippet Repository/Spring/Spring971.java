	@SuppressWarnings("")
	@Test
	public void setPropertyIntermediateListIsNullWithAutoGrow() {
		Foo target = new Foo();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setConversionService(new DefaultConversionService());
		accessor.setAutoGrowNestedPaths(true);
		Map<String, String> map = new HashMap<>();
		map.put("favoriteNumber", "9");
		accessor.setPropertyValue("list[0]", map);
		assertEquals(map, target.list.get(0));
	}
