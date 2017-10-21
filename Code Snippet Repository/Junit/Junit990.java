	@Test
	void testWithImmeasurableTimeIsOutputCorrectly(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("test", () -> {
		});

		executeTests(engine, tempDirectory, Clock.fixed(Instant.EPOCH, ZoneId.systemDefault()));

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite",
				"<testcase name=\"test\" classname=\"dummy\" time=\"0\"");
		//@formatter:on
	}
