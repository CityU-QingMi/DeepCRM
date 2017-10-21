	@Test
	public void compareEmpty() {
		HttpServletRequest request = new MockHttpServletRequest();

		RequestConditionHolder empty = new RequestConditionHolder(null);
		RequestConditionHolder empty2 = new RequestConditionHolder(null);
		RequestConditionHolder notEmpty = new RequestConditionHolder(new ParamsRequestCondition("name"));

		assertEquals(0, empty.compareTo(empty2, request));
		assertEquals(-1, notEmpty.compareTo(empty, request));
		assertEquals(1, empty.compareTo(notEmpty, request));
	}
