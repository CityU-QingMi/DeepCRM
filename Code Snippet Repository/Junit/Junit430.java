	@Test
	void inheritedNonGenericMethodsAreExecuted() {
		String b = execute(4, AbstractNonGenericTests.B.class);
		assertAll("Missing expected test(s) in sequence: " + b, //
			() -> assertTrue(b.contains("A.test(Number)")), //
			() -> assertTrue(b.contains("mA()")), //
			() -> assertTrue(b.contains("mB()")), //
			() -> assertTrue(b.contains("B.test(Byte)")) //
		);
		String c = execute(5, AbstractNonGenericTests.C.class);
		assertAll("Missing expected test(s) in sequence: " + c, //
			() -> assertTrue(c.contains("A.test(Number)")), //
			() -> assertTrue(c.contains("mA()")), //
			() -> assertTrue(c.contains("mB()")), //
			() -> assertTrue(c.contains("mC()")), //
			() -> assertTrue(c.contains("C.test(Byte)")) //
		);
	}
