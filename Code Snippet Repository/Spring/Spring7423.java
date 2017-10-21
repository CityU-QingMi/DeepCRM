	@Test
	public void combineOnePatternWithEmptySet() {
		DestinationPatternsMessageCondition c1 = condition("/type1", "/type2");
		DestinationPatternsMessageCondition c2 = condition();

		assertEquals(condition("/type1", "/type2"), c1.combine(c2));

		c1 = condition();
		c2 = condition("/method1", "/method2");

		assertEquals(condition("/method1", "/method2"), c1.combine(c2));
	}
