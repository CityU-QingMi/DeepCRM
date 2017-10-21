	@Test
	void writesHostNameAndTimestamp(@Root Path tempDirectory) throws Exception {
		DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
		engine.addTest("test", () -> {
		});

		LocalDateTime now = LocalDateTime.parse("2016-01-28T14:02:59.123");
		ZoneId zone = ZoneId.systemDefault();

		executeTests(engine, tempDirectory, Clock.fixed(ZonedDateTime.of(now, zone).toInstant(), zone));

		String content = readValidXmlFile(tempDirectory.resolve("TEST-dummy.xml"));

		//@formatter:off
		assertThat(content)
			.containsSequence(
				"<testsuite",
				"hostname=\"" + InetAddress.getLocalHost().getHostName() + "\"",
				"timestamp=\"2016-01-28T14:02:59\"",
				"<testcase",
				"</testsuite>");
		//@formatter:on
	}
