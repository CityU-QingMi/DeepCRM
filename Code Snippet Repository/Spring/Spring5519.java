	@Test
	public void testDeleteAny() throws Exception {
		String inString = "Able was I ere I saw Elba";

		String res = StringUtils.deleteAny(inString, "I");
		assertTrue("Result has no Is [" + res + "]", res.equals("Able was  ere  saw Elba"));

		res = StringUtils.deleteAny(inString, "AeEba!");
		assertTrue("Result has no Is [" + res + "]", res.equals("l ws I r I sw l"));

		String mismatch = StringUtils.deleteAny(inString, "#@$#$^");
		assertTrue("Result is unchanged", mismatch.equals(inString));

		String whitespace = "This is\n\n\n    \t   a messagy string with whitespace\n";
		assertTrue("Has CR", whitespace.contains("\n"));
		assertTrue("Has tab", whitespace.contains("\t"));
		assertTrue("Has  sp", whitespace.contains(" "));
		String cleaned = StringUtils.deleteAny(whitespace, "\n\t ");
		assertTrue("Has no CR", !cleaned.contains("\n"));
		assertTrue("Has no tab", !cleaned.contains("\t"));
		assertTrue("Has no sp", !cleaned.contains(" "));
		assertTrue("Still has chars", cleaned.length() > 10);
	}
