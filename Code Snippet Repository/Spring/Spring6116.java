	@Test
	public void testStringLiterals_DoubleQuotes_spr9620_2() {
		try {
			new SpelExpressionParser().parseRaw("\"double quote: \\\"\\\".\"");
			fail("Should have failed");
		}
		catch (SpelParseException spe) {
			assertEquals(17, spe.getPosition());
			assertEquals(SpelMessage.UNEXPECTED_ESCAPE_CHAR, spe.getMessageCode());
		}
	}
