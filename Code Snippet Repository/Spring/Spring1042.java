	@Test
	public void testGenericListOfListsWithElementConversion() throws MalformedURLException {
		GenericBean<String> gb = new GenericBean<>();
		List<List<Integer>> list = new LinkedList<>();
		list.add(new LinkedList<>());
		gb.setListOfLists(list);
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("listOfLists[0][0]", "5");
		assertEquals(new Integer(5), bw.getPropertyValue("listOfLists[0][0]"));
		assertEquals(new Integer(5), gb.getListOfLists().get(0).get(0));
	}
