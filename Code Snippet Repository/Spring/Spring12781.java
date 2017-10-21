	@Test
	public void combineOnePatternWithEmptySet() {
		PatternsRequestCondition c1 = new PatternsRequestCondition("/type1", "/type2");
		PatternsRequestCondition c2 = new PatternsRequestCondition();

		assertEquals(new PatternsRequestCondition("/type1", "/type2"), c1.combine(c2));

		c1 = new PatternsRequestCondition();
		c2 = new PatternsRequestCondition("/method1", "/method2");

		assertEquals(new PatternsRequestCondition("/method1", "/method2"), c1.combine(c2));
	}
