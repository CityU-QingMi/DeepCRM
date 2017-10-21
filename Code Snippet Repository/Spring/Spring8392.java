	private void runTestAndVerifyHierarchies(Class<? extends FooTestCase> testClass, boolean isFooContextActive,
			boolean isBarContextActive, boolean isBazContextActive) {

		JUnitCore jUnitCore = new JUnitCore();
		Result result = jUnitCore.run(testClass);
		assertTrue("all tests passed", result.wasSuccessful());

		assertThat(ContextHierarchyDirtiesContextTests.context, notNullValue());

		ConfigurableApplicationContext bazContext = (ConfigurableApplicationContext) ContextHierarchyDirtiesContextTests.context;
		assertEquals("baz", ContextHierarchyDirtiesContextTests.baz);
		assertThat("bazContext#isActive()", bazContext.isActive(), is(isBazContextActive));

		ConfigurableApplicationContext barContext = (ConfigurableApplicationContext) bazContext.getParent();
		assertThat(barContext, notNullValue());
		assertEquals("bar", ContextHierarchyDirtiesContextTests.bar);
		assertThat("barContext#isActive()", barContext.isActive(), is(isBarContextActive));

		ConfigurableApplicationContext fooContext = (ConfigurableApplicationContext) barContext.getParent();
		assertThat(fooContext, notNullValue());
		assertEquals("foo", ContextHierarchyDirtiesContextTests.foo);
		assertThat("fooContext#isActive()", fooContext.isActive(), is(isFooContextActive));
	}
