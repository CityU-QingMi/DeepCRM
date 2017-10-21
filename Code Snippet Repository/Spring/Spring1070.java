	@Test
	public void testGenericMapElementWithCollectionValue() {
		GenericBean<?> gb = new GenericBean<>();
		gb.setCollectionMap(new HashMap<>());
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.registerCustomEditor(Number.class, new CustomNumberEditor(Integer.class, false));
		HashSet<Integer> value1 = new HashSet<>();
		value1.add(new Integer(1));
		bw.setPropertyValue("collectionMap[1]", value1);
		assertTrue(gb.getCollectionMap().get(new Integer(1)) instanceof HashSet);
	}
