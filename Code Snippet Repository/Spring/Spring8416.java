	@Test
	public void verifyCacheKeyIsBasedOnActiveProfiles() {
		int size = 0, hit = 0, miss = 0;
		loadCtxAndAssertStats(FooBarProfilesTestCase.class, ++size, hit, ++miss);
		loadCtxAndAssertStats(FooBarProfilesTestCase.class, size, ++hit, miss);
		// Profiles {foo, bar} should not hash to the same as {bar,foo}
		loadCtxAndAssertStats(BarFooProfilesTestCase.class, ++size, hit, ++miss);
		loadCtxAndAssertStats(FooBarProfilesTestCase.class, size, ++hit, miss);
		loadCtxAndAssertStats(FooBarProfilesTestCase.class, size, ++hit, miss);
		loadCtxAndAssertStats(BarFooProfilesTestCase.class, size, ++hit, miss);
		loadCtxAndAssertStats(FooBarActiveProfilesResolverTestCase.class, size, ++hit, miss);
	}
