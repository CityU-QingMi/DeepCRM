	@Test
	public void testGenericMapOfLists() throws MalformedURLException {
		GenericBean<String> gb = new GenericBean<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		map.put(new Integer(1), new LinkedList<>());
		gb.setMapOfLists(map);
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("mapOfLists[1][0]", new Integer(5));
		assertEquals(new Integer(5), bw.getPropertyValue("mapOfLists[1][0]"));
		assertEquals(new Integer(5), gb.getMapOfLists().get(new Integer(1)).get(0));
	}
