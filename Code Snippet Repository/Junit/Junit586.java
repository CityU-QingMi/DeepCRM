	@Test
	void excludeNames() {
		assertAll("exclude name with none excluded", //
			() -> assertTrue(EXCLUDE.select(FOO, Collections.emptySet())),
			() -> assertTrue(EXCLUDE.select(BAR, Collections.emptySet())),
			() -> assertTrue(EXCLUDE.select(BAZ, Collections.emptySet())));
		assertAll("exclude name with FOO excluded", //
			() -> assertFalse(EXCLUDE.select(FOO, FOO.singleton())),
			() -> assertTrue(EXCLUDE.select(BAR, FOO.singleton())),
			() -> assertTrue(EXCLUDE.select(BAZ, FOO.singleton())));
	}
