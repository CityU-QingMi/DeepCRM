	@Test
	public void testGenericLowerBoundedSet() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		bw.registerCustomEditor(Number.class, new CustomNumberEditor(Integer.class, true));
		Set<String> input = new HashSet<>();
		input.add("4");
		input.add("5");
		bw.setPropertyValue("numberSet", input);
		assertTrue(gb.getNumberSet().contains(new Integer(4)));
		assertTrue(gb.getNumberSet().contains(new Integer(5)));
	}
