	@Test
	public void testCopySameHeaderValuesNotModifiedSameMessage() throws Exception {
		Date current = new Date();
		Map<String, Object> originalHeaders = new HashMap<>();
		originalHeaders.put("b", "xyz");
		originalHeaders.put("c", current);
		Message<?> original = MessageBuilder.withPayload("foo").setHeader("a", 123).copyHeaders(originalHeaders).build();
		Map<String, Object> newHeaders = new HashMap<>();
		newHeaders.put("a", 123);
		newHeaders.put("b", "xyz");
		newHeaders.put("c", current);
		Message<?> result = MessageBuilder.fromMessage(original).copyHeaders(newHeaders).build();
		assertEquals(original, result);
	}
