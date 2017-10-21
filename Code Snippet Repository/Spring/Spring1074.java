	@Test
	public void notWritablePropertyExceptionContainsAlternativeMatch() {
		IntelliBean target = new IntelliBean();
		BeanWrapper bw = createAccessor(target);
		try {
			bw.setPropertyValue("names", "Alef");
		}
		catch (NotWritablePropertyException ex) {
			assertNotNull("Possible matches not determined", ex.getPossibleMatches());
			assertEquals("Invalid amount of alternatives", 1, ex.getPossibleMatches().length);
		}
	}
