	@Test
	void containsTestsForTests() {
		engineDescriptor.addChild(
			new AbstractTestDescriptor(engineDescriptor.getUniqueId().append("test", "bar"), "Bar") {
				@Override
				public Type getType() {
					return Type.TEST;
				}
			});

		TestPlan testPlan = TestPlan.from(singleton(engineDescriptor));

		assertThat(testPlan.containsTests()).as("contains tests").isTrue();
	}
