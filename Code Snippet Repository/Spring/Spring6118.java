	@Test
	public void tokenKind() {
		TokenKind tk = TokenKind.NOT;
		assertFalse(tk.hasPayload());
		assertEquals("NOT(!)", tk.toString());

		tk = TokenKind.MINUS;
		assertFalse(tk.hasPayload());
		assertEquals("MINUS(-)", tk.toString());

		tk = TokenKind.LITERAL_STRING;
		assertEquals("LITERAL_STRING", tk.toString());
		assertTrue(tk.hasPayload());
	}
