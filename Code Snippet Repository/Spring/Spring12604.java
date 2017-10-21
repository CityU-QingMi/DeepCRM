	@Test
	public void addTargetRequestParamsNullKey() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add(" ", "abc");
		params.add(null, " ");

		FlashMap flashMap = new FlashMap();
		flashMap.addTargetRequestParams(params);

		assertTrue(flashMap.getTargetRequestParams().isEmpty());
	}
