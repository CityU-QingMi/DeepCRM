	@Test
	public void map() throws Exception {
		String body = "{\"bar\":\"b1\",\"foo\":\"f1\"}";
		Map<String, String> map = new HashMap<>();
		map.put("foo", "f1");
		map.put("bar", "b1");
		ResolvableType type = forClassWithGenerics(Map.class, String.class, String.class);
		MethodParameter param = this.testMethod.arg(type);
		Map<String, String> actual = resolveValue(param, body);

		assertEquals(map, actual);
	}
