	@Test
	public void testNesting() throws Exception {
		MockEntry one = new MockEntry();
		MockEntry two = new MockEntry();
		MockEntry three = new MockEntry();

		ParseState parseState = new ParseState();
		parseState.push(one);
		assertEquals(one, parseState.peek());
		parseState.push(two);
		assertEquals(two, parseState.peek());
		parseState.push(three);
		assertEquals(three, parseState.peek());

		parseState.pop();
		assertEquals(two, parseState.peek());
		parseState.pop();
		assertEquals(one, parseState.peek());
	}
