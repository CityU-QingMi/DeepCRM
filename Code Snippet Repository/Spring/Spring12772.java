	@Test
	public void compare() {
		HttpServletRequest request = new MockHttpServletRequest();

		CompositeRequestCondition cond1 = new CompositeRequestCondition(this.param1);
		CompositeRequestCondition cond3 = new CompositeRequestCondition(this.param3);

		assertEquals(1, cond1.compareTo(cond3, request));
		assertEquals(-1, cond3.compareTo(cond1, request));
	}
