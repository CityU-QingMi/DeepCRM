	@Test
	public void testSizeFunctionAndProperty() {
		assertTranslation("from Animal a where a.offspring.size > 0");
		assertTranslation("from Animal a join a.offspring where a.offspring.size > 1");
		assertTranslation("from Animal a where size(a.offspring) > 0");
		assertTranslation("from Animal a join a.offspring o where size(a.offspring) > 1");
		assertTranslation("from Animal a where size(a.offspring) > 1 and size(a.offspring) < 100");

		assertTranslation("from Human a where a.family.size > 0");
		assertTranslation("from Human a join a.family where a.family.size > 1");
		assertTranslation("from Human a where size(a.family) > 0");
		assertTranslation("from Human a join a.family o where size(a.family) > 1");
		assertTranslation("from Human a where a.family.size > 0 and a.family.size < 100");
	}
