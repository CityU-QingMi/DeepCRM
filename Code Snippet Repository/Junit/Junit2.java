	@Test
	void testInAllEnvironments() {
		assumingThat("CI".equals(System.getenv("ENV")),
			() -> {
				// perform these assertions only on the CI server
				assertEquals(2, 2);
			});

		// perform these assertions in all environments
		assertEquals("a string", "a string");
	}
