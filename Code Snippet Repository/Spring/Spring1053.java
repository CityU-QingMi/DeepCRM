	@Test
	public void testGenericTypeNestingMapOfListOfInteger() throws Exception {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		map.put("testKey", list);

		NestedGenericCollectionBean gb = new NestedGenericCollectionBean();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("mapOfListOfInteger", map);

		Object obj = gb.getMapOfListOfInteger().get("testKey").get(0);
		assertTrue(obj instanceof Integer);
		assertEquals(1, ((Integer) obj).intValue());
	}
