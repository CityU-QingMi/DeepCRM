	@Test
	public void testRogueTrailingDotCausesNPE_SPR6866() {
		try {
			new SpelExpressionParser().parseExpression("placeOfBirth.foo.");
			fail("Should have failed to parse");
		}
		catch (ParseException e) {
			e.printStackTrace();
			assertTrue(e instanceof SpelParseException);
			SpelParseException spe = (SpelParseException) e;
			assertEquals(SpelMessage.OOD, spe.getMessageCode());
			assertEquals(16, spe.getPosition());
		}
	}
