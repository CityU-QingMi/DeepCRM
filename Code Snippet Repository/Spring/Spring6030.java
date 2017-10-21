	@Test
	public void testDictionaryAccess() throws Exception {
		StandardEvaluationContext societyContext = new StandardEvaluationContext();
		societyContext.setRootObject(new IEEE());
		// Officer's Dictionary
		Inventor pupin = parser.parseExpression("officers['president']").getValue(societyContext, Inventor.class);
		assertNotNull(pupin);

		// evaluates to "Idvor"
		String city = parser.parseExpression("officers['president'].PlaceOfBirth.city").getValue(societyContext, String.class);
		assertNotNull(city);

		// setting values
		Inventor i = parser.parseExpression("officers['advisors'][0]").getValue(societyContext,Inventor.class);
		assertEquals("Nikola Tesla",i.getName());

		parser.parseExpression("officers['advisors'][0].PlaceOfBirth.Country").setValue(societyContext, "Croatia");

		Inventor i2 = parser.parseExpression("reverse[0]['advisors'][0]").getValue(societyContext,Inventor.class);
		assertEquals("Nikola Tesla",i2.getName());

	}
