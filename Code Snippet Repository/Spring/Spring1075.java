	@Test
	public void notWritablePropertyExceptionContainsAlternativeMatches() {
		IntelliBean target = new IntelliBean();
		BeanWrapper bw = createAccessor(target);
		try {
			bw.setPropertyValue("mystring", "Arjen");
		}
		catch (NotWritablePropertyException ex) {
			assertNotNull("Possible matches not determined", ex.getPossibleMatches());
			assertEquals("Invalid amount of alternatives", 3, ex.getPossibleMatches().length);
		}
	}
