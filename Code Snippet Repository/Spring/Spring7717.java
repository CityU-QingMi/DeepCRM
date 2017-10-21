	@Test
	public void createFromNativeHeaderMap() {
		MultiValueMap<String, String> inputNativeHeaders = new LinkedMultiValueMap<>();
		inputNativeHeaders.add("foo", "bar");
		inputNativeHeaders.add("bar", "baz");

		NativeMessageHeaderAccessor headerAccessor = new NativeMessageHeaderAccessor(inputNativeHeaders);
		Map<String, Object> actual = headerAccessor.toMap();

		assertEquals(actual.toString(), 1, actual.size());
		assertNotNull(actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS));
		assertEquals(inputNativeHeaders, actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS));
		assertNotSame(inputNativeHeaders, actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS));
	}
