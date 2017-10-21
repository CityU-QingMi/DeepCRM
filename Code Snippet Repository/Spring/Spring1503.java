	@Test
	public void testSimple() throws Exception {
		MockEntry entry = new MockEntry();

		ParseState parseState = new ParseState();
		parseState.push(entry);
		assertEquals("Incorrect peek value.", entry, parseState.peek());
		parseState.pop();
		assertNull("Should get null on peek()", parseState.peek());
	}
