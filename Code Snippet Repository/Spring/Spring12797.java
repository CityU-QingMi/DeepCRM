	@Test
	public void compare() {
		HttpServletRequest request = new MockHttpServletRequest();

		RequestConditionHolder params11 = new RequestConditionHolder(new ParamsRequestCondition("1"));
		RequestConditionHolder params12 = new RequestConditionHolder(new ParamsRequestCondition("1", "2"));

		assertEquals(1, params11.compareTo(params12, request));
		assertEquals(-1, params12.compareTo(params11, request));
	}
