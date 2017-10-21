	@Test
	public void token() {
		Token token = new Token(TokenKind.NOT, 0, 3);
		assertEquals(TokenKind.NOT, token.kind);
		assertEquals(0, token.startPos);
		assertEquals(3, token.endPos);
		assertEquals("[NOT(!)](0,3)", token.toString());

		token = new Token(TokenKind.LITERAL_STRING, "abc".toCharArray(), 0, 3);
		assertEquals(TokenKind.LITERAL_STRING, token.kind);
		assertEquals(0, token.startPos);
		assertEquals(3, token.endPos);
		assertEquals("[LITERAL_STRING:abc](0,3)", token.toString());
	}
