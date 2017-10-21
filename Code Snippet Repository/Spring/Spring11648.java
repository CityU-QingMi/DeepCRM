	@Test
	public void combineOnePatternWithEmptySet() {
		PatternsRequestCondition c1 = createPatternsCondition("/type1", "/type2");
		PatternsRequestCondition c2 = new PatternsRequestCondition();

		assertEquals(createPatternsCondition("/type1", "/type2"), c1.combine(c2));

		c1 = new PatternsRequestCondition();
		c2 = createPatternsCondition("/method1", "/method2");

		assertEquals(createPatternsCondition("/method1", "/method2"), c1.combine(c2));
	}
