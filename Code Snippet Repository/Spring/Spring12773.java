	@Test
	public void compareEmpty() {
		HttpServletRequest request = new MockHttpServletRequest();

		CompositeRequestCondition empty = new CompositeRequestCondition();
		CompositeRequestCondition notEmpty = new CompositeRequestCondition(this.param1);

		assertEquals(0, empty.compareTo(empty, request));
		assertEquals(-1, notEmpty.compareTo(empty, request));
		assertEquals(1, empty.compareTo(notEmpty, request));
	}
