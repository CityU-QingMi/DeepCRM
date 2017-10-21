	@Test
	public void setMapPropertyNonMatchingType() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		Map<String, String> map = new TreeMap<>();
		map.put("key", "value");
		accessor.setPropertyValue("map", map);
		Map<String, String> sortedMap = new TreeMap<>();
		sortedMap.put("sortedKey", "sortedValue");
		accessor.setPropertyValue("sortedMap", sortedMap);
		assertEquals(1, target.getMap().size());
		assertEquals("value", target.getMap().get("key"));
		assertEquals(1, target.getSortedMap().size());
		assertEquals("sortedValue", target.getSortedMap().get("sortedKey"));
	}
