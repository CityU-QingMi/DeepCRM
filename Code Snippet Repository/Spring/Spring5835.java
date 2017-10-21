	@Test
	public void testPropertiesNested03() throws ParseException {
		try {
			new SpelExpressionParser().parseRaw("placeOfBirth.23");
			fail();
		}
		catch (SpelParseException spe) {
			assertEquals(spe.getMessageCode(), SpelMessage.UNEXPECTED_DATA_AFTER_DOT);
			assertEquals("23", spe.getInserts()[0]);
		}
	}
