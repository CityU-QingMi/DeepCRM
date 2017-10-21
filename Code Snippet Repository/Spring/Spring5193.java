	@Test
	public void convertMapToMap() throws Exception {
		Map<String, String> foo = new HashMap<>();
		foo.put("1", "BAR");
		foo.put("2", "BAZ");
		@SuppressWarnings("unchecked")
		Map<Integer, FooEnum> map = (Map<Integer, FooEnum>) conversionService.convert(foo,
				TypeDescriptor.forObject(foo), new TypeDescriptor(getClass().getField("genericMap")));
		assertEquals(FooEnum.BAR, map.get(1));
		assertEquals(FooEnum.BAZ, map.get(2));
	}
