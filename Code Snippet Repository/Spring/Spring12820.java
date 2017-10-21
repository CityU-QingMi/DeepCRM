	@Test
	public void createEmpty() {
		RequestMappingInfo info = paths().build();

		assertEquals(0, info.getPatternsCondition().getPatterns().size());
		assertEquals(0, info.getMethodsCondition().getMethods().size());
		assertEquals(true, info.getConsumesCondition().isEmpty());
		assertEquals(true, info.getProducesCondition().isEmpty());
		assertNotNull(info.getParamsCondition());
		assertNotNull(info.getHeadersCondition());
		assertNull(info.getCustomCondition());
	}
