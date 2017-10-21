	@Test
	public void compareEmpty() {
		CompositeRequestCondition empty = new CompositeRequestCondition();
		CompositeRequestCondition notEmpty = new CompositeRequestCondition(this.param1);
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());

		assertEquals(0, empty.compareTo(empty, exchange));
		assertEquals(-1, notEmpty.compareTo(empty, exchange));
		assertEquals(1, empty.compareTo(notEmpty, exchange));
	}
