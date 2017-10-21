	@Test
	void doesNotContainTestsForEmptyContainers() {
		engineDescriptor.addChild(
			new AbstractTestDescriptor(engineDescriptor.getUniqueId().append("test", "bar"), "Bar") {
				@Override
				public Type getType() {
					return Type.CONTAINER;
				}
			});

		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));

		assertThat(testPlan.containsTests()).as("contains tests").isFalse();
	}
