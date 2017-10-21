	@Test
	public void addTargetRequestParamsNullValue() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "abc");
		params.add("key", " ");
		params.add("key", null);

		FlashMap flashMap = new FlashMap();
		flashMap.addTargetRequestParams(params);

		assertEquals(1, flashMap.getTargetRequestParams().size());
		assertEquals(1, flashMap.getTargetRequestParams().get("key").size());
		assertEquals("abc", flashMap.getTargetRequestParams().getFirst("key"));
	}
