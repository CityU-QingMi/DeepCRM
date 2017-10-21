	@Test
	public void setMapProperty() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		Map<String, String> map = new HashMap<>();
		map.put("key", "value");
		accessor.setPropertyValue("map", map);
		SortedMap<?, ?> sortedMap = new TreeMap<>();
		map.put("sortedKey", "sortedValue");
		accessor.setPropertyValue("sortedMap", sortedMap);
		assertSame(map, target.getMap());
		assertSame(sortedMap, target.getSortedMap());
	}
