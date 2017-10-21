	@Test(expected = Exception.class)
	public void checkThatExceptionsAreNotSilentlySwallowed() throws Exception {
		SpringJUnit4ClassRunner runner = new SpringJUnit4ClassRunner(getClass()) {

			@Override
			protected TestContextManager createTestContextManager(Class<?> clazz) {
				return new TestContextManager(clazz) {

					@Override
					public void prepareTestInstance(Object testInstance) {
						throw new RuntimeException(
							"This RuntimeException should be caught and wrapped in an Exception.");
					}
				};
			}
		};
		runner.createTest();
	}
