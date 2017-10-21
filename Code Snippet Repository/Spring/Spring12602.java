	@Test
	public void addTargetRequestParamNullValue() {
		FlashMap flashMap = new FlashMap();
		flashMap.addTargetRequestParam("text", "abc");
		flashMap.addTargetRequestParam("empty", " ");
		flashMap.addTargetRequestParam("null", null);

		assertEquals(1, flashMap.getTargetRequestParams().size());
		assertEquals("abc", flashMap.getTargetRequestParams().getFirst("text"));
	}
