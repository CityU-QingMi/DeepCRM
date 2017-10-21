	@Test
	public void compareEmpty() {
		RequestConditionHolder empty = new RequestConditionHolder(null);
		RequestConditionHolder empty2 = new RequestConditionHolder(null);
		RequestConditionHolder notEmpty = new RequestConditionHolder(new ParamsRequestCondition("name"));

		assertEquals(0, empty.compareTo(empty2, this.exchange));
		assertEquals(-1, notEmpty.compareTo(empty, this.exchange));
		assertEquals(1, empty.compareTo(notEmpty, this.exchange));
	}
