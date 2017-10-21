	@Test
	public void testGenericTypeNestingMapOfListOfListOfInteger() throws Exception {
		Map<String, List<List<String>>> map = new HashMap<>();
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		map.put("testKey", Collections.singletonList(list));

		NestedGenericCollectionBean gb = new NestedGenericCollectionBean();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("mapOfListOfListOfInteger", map);

		Object obj = gb.getMapOfListOfListOfInteger().get("testKey").get(0).get(0);
		assertTrue(obj instanceof Integer);
		assertEquals(1, ((Integer) obj).intValue());
	}
