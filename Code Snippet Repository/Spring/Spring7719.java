	@Test
	public void createFromMessage() {
		MultiValueMap<String, String> inputNativeHeaders = new LinkedMultiValueMap<>();
		inputNativeHeaders.add("foo", "bar");
		inputNativeHeaders.add("bar", "baz");

		Map<String, Object> inputHeaders = new HashMap<>();
		inputHeaders.put("a", "b");
		inputHeaders.put(NativeMessageHeaderAccessor.NATIVE_HEADERS, inputNativeHeaders);

		GenericMessage<String> message = new GenericMessage<>("p", inputHeaders);
		NativeMessageHeaderAccessor headerAccessor = new NativeMessageHeaderAccessor(message);
		Map<String, Object> actual = headerAccessor.toMap();

		assertEquals(2, actual.size());
		assertEquals("b", actual.get("a"));
		assertNotNull(actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS));
		assertEquals(inputNativeHeaders, actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS));
		assertNotSame(inputNativeHeaders, actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS));
	}
