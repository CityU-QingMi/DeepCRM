	@Test
	public void convertParamMapToArray() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("a", "a");
		paramMap.put("b", "b");
		paramMap.put("c", "c");
		assertSame(3, NamedParameterUtils.buildValueArray("xxx :a :b :c", paramMap).length);
		assertSame(5, NamedParameterUtils.buildValueArray("xxx :a :b :c xx :a :b", paramMap).length);
		assertSame(5, NamedParameterUtils.buildValueArray("xxx :a :a :a xx :a :a", paramMap).length);
		assertEquals("b", NamedParameterUtils.buildValueArray("xxx :a :b :c xx :a :b", paramMap)[4]);
		try {
			NamedParameterUtils.buildValueArray("xxx :a :b ?", paramMap);
			fail("mixed named parameters and ? placeholders not detected");
		}
		catch (InvalidDataAccessApiUsageException expected) {
		}
	}
