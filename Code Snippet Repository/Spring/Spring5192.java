	@Test
	public void collection() {
		List<String> strings = new ArrayList<>();
		strings.add("3");
		strings.add("9");
		@SuppressWarnings("unchecked")
		List<Integer> integers = (List<Integer>) conversionService.convert(strings,
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Integer.class)));
		assertEquals(new Integer(3), integers.get(0));
		assertEquals(new Integer(9), integers.get(1));
	}
