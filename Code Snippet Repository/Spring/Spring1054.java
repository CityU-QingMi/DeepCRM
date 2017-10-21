	@Test
	public void testGenericTypeNestingListOfMapOfInteger() throws Exception {
		List<Map<String, String>> list = new LinkedList<>();
		Map<String, String> map = new HashMap<>();
		map.put("testKey", "5");
		list.add(map);

		NestedGenericCollectionBean gb = new NestedGenericCollectionBean();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("listOfMapOfInteger", list);

		Object obj = gb.getListOfMapOfInteger().get(0).get("testKey");
		assertTrue(obj instanceof Integer);
		assertEquals(5, ((Integer) obj).intValue());
	}
