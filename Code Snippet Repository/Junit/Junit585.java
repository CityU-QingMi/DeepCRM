	@Test
	void includeNamesWithSingleton() {
		assertAll("include names with singleton", //
			() -> assertTrue(INCLUDE.select(FOO, FOO.singleton())),
			() -> assertTrue(INCLUDE.select(BAR, BAR.singleton())),
			() -> assertTrue(INCLUDE.select(BAZ, BAZ.singleton())));
		assertAll("include names with singleton complement", //
			() -> assertFalse(INCLUDE.select(BAR, FOO.singleton())),
			() -> assertFalse(INCLUDE.select(BAZ, FOO.singleton())));
	}
