	@Test
	public void map() {
		Map<String, String> strings = new HashMap<>();
		strings.put("3", "9");
		strings.put("6", "31");
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> integers = (Map<Integer, Integer>) conversionService.convert(strings,
				TypeDescriptor.map(Map.class, TypeDescriptor.valueOf(Integer.class), TypeDescriptor.valueOf(Integer.class)));
		assertEquals(new Integer(9), integers.get(3));
		assertEquals(new Integer(31), integers.get(6));
	}
