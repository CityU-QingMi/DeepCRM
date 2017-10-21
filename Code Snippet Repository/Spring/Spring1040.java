	@Test
	public void testGenericSet() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		Set<String> input = new HashSet<>();
		input.add("4");
		input.add("5");
		bw.setPropertyValue("integerSet", input);
		assertTrue(gb.getIntegerSet().contains(new Integer(4)));
		assertTrue(gb.getIntegerSet().contains(new Integer(5)));
	}
