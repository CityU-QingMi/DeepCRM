	@Test
	public void testGenericMapWithKeyType() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		Map<String, String> input = new HashMap<>();
		input.put("4", "5");
		input.put("6", "7");
		bw.setPropertyValue("longMap", input);
		assertEquals("5", gb.getLongMap().get(new Long("4")));
		assertEquals("7", gb.getLongMap().get(new Long("6")));
	}
