	@Test
	public void testSnapshot() throws Exception {
		MockEntry entry = new MockEntry();

		ParseState original = new ParseState();
		original.push(entry);

		ParseState snapshot = original.snapshot();
		original.push(new MockEntry());
		assertEquals("Snapshot should not have been modified.", entry, snapshot.peek());
	}
