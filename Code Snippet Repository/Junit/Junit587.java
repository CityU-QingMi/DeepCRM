	@Test
	void matchesAll() {
		assertAll("matches all", //
			() -> assertTrue(MATCH_ALL.select(FOO, Collections.singleton("F.."))),
			() -> assertTrue(MATCH_ALL.select(BAR, Collections.singleton("B.."))),
			() -> assertTrue(MATCH_ALL.select(BAZ, Collections.singleton("B.."))));
		assertAll("matches all fails if not all match", //
			() -> assertFalse(MATCH_ALL.select(FOO, set("F..", "."))),
			() -> assertFalse(MATCH_ALL.select(BAR, set("B..", "."))),
			() -> assertFalse(MATCH_ALL.select(BAZ, set("B..", "."))));
	}
