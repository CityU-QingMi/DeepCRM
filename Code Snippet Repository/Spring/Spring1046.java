	@Test
	public void testGenericListOfMapsWithElementConversion() throws MalformedURLException {
		GenericBean<String> gb = new GenericBean<>();
		List<Map<Integer, Long>> list = new LinkedList<>();
		list.add(new HashMap<>());
		gb.setListOfMaps(list);
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("listOfMaps[0][10]", "5");
		assertEquals(new Long(5), bw.getPropertyValue("listOfMaps[0][10]"));
		assertEquals(new Long(5), gb.getListOfMaps().get(0).get(10));
	}
