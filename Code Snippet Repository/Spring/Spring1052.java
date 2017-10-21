	@Test
	public void testGenericTypeNestingMapOfInteger() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("testKey", "100");

		NestedGenericCollectionBean gb = new NestedGenericCollectionBean();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("mapOfInteger", map);

		Object obj = gb.getMapOfInteger().get("testKey");
		assertTrue(obj instanceof Integer);
	}
