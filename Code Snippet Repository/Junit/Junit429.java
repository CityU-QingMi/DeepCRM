	@TestFactory
	List<DynamicTest> ensureSingleTestMethodsExecute() {
		return Arrays.asList(
			dynamicTest("Byte", //
				() -> assertEquals("[test(Byte) BEGIN, test(N), test(Byte) END, test(Long) BEGIN, test(Long) END]", //
					execute(2, ByteTestCase.class))),
			dynamicTest("Short", //
				() -> assertEquals("[test(Long) BEGIN, test(Long) END, test(Short) BEGIN, test(N), test(Short) END]", //
					execute(2, ShortTestCase.class))));
	}
