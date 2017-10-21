	@Test
	void assertLinesMatchParseFastForwardLimit() {
		assertAll("valid fast-forward limits", //
			() -> assertEquals(Integer.MAX_VALUE, parseFastForwardLimit(">>>>")),
			() -> assertEquals(Integer.MAX_VALUE, parseFastForwardLimit(">> >>")),
			() -> assertEquals(Integer.MAX_VALUE, parseFastForwardLimit(">> stacktrace >>")),
			() -> assertEquals(Integer.MAX_VALUE, parseFastForwardLimit(">> non Integer.parse()-able comment >>")),
			() -> assertEquals(9, parseFastForwardLimit(">>9>>")),
			() -> assertEquals(9, parseFastForwardLimit(">> 9 >>")));
		Throwable error = assertThrows(PreconditionViolationException.class, () -> parseFastForwardLimit(">>0>>"));
		assertMessageEquals(error, "fast-forward(0) limit must be greater than zero");
		error = assertThrows(PreconditionViolationException.class, () -> parseFastForwardLimit(">>-1>>"));
		assertMessageEquals(error, "fast-forward(-1) limit must be greater than zero");
		error = assertThrows(PreconditionViolationException.class, () -> parseFastForwardLimit(">>-2147483648>>"));
		assertMessageEquals(error, "fast-forward(-2147483648) limit must be greater than zero");
	}
