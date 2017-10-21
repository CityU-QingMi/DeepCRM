	@Test
	void measuresTimesInSeconds(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("firstTest", () -> {
		});
		engine.addTest("secondTest", () -> {
		});

		executeTests(engine, tempDirectory, new IncrementingClock(0, Duration.ofMillis(333)));

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		//               start        end
		// ----------- ---------- -----------
		// engine          0 (1)    1,665 (6)
		// firstTest     333 (2)      666 (3)
		// secondTest    999 (4)    1,332 (5)
		assertThat(content)
			.containsSequence(
				"<testsuite", "time=\"1.665\"",
				"<testcase name=\"firstTest\" classname=\"dummy\" time=\"0.333\"",
				"<testcase name=\"secondTest\" classname=\"dummy\" time=\"0.333\"");
		//@formatter:on
	}
