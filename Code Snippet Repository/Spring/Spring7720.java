	@Test
	public void createFromMessageNull() {
		NativeMessageHeaderAccessor headerAccessor = new NativeMessageHeaderAccessor((Message<?>) null);

		Map<String, Object> actual = headerAccessor.toMap();
		assertEquals(0, actual.size());

		Map<String, List<String>> actualNativeHeaders = headerAccessor.toNativeHeaderMap();
		assertEquals(Collections.emptyMap(), actualNativeHeaders);
	}
