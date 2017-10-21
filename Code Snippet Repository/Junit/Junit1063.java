	@Test
	void containsTestsForContainersThatMayRegisterTests() {
		engineDescriptor.addChild(
			new AbstractTestDescriptor(engineDescriptor.getUniqueId().append("test", "bar"), "Bar") {
				@Override
				public Type getType() {
					return Type.CONTAINER;
				}

				@Override
				public boolean mayRegisterTests() {
					return true;
				}
			});

		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));

		assertThat(testPlan.containsTests()).as("contains tests").isTrue();
	}
