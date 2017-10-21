	@Test
	public void testGenericMapOfMaps() throws MalformedURLException {
		GenericBean<String> gb = new GenericBean<>();
		Map<String, Map<Integer, Long>> map = new HashMap<>();
		map.put("mykey", new HashMap<>());
		gb.setMapOfMaps(map);
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.setPropertyValue("mapOfMaps[mykey][10]", new Long(5));
		assertEquals(new Long(5), bw.getPropertyValue("mapOfMaps[mykey][10]"));
		assertEquals(new Long(5), gb.getMapOfMaps().get("mykey").get(10));
	}
