	@Test
	public void createFromMessageAndModify() {

		MultiValueMap<String, String> inputNativeHeaders = new LinkedMultiValueMap<>();
		inputNativeHeaders.add("foo", "bar");
		inputNativeHeaders.add("bar", "baz");

		Map<String, Object> nativeHeaders = new HashMap<>();
		nativeHeaders.put("a", "b");
		nativeHeaders.put(NativeMessageHeaderAccessor.NATIVE_HEADERS, inputNativeHeaders);

		GenericMessage<String> message = new GenericMessage<>("p", nativeHeaders);

		NativeMessageHeaderAccessor headerAccessor = new NativeMessageHeaderAccessor(message);
		headerAccessor.setHeader("a", "B");
		headerAccessor.setNativeHeader("foo", "BAR");

		Map<String, Object> actual = headerAccessor.toMap();

		assertEquals(2, actual.size());
		assertEquals("B", actual.get("a"));

		@SuppressWarnings("unchecked")
		Map<String, List<String>> actualNativeHeaders =
				(Map<String, List<String>>) actual.get(NativeMessageHeaderAccessor.NATIVE_HEADERS);

		assertNotNull(actualNativeHeaders);
		assertEquals(Arrays.asList("BAR"), actualNativeHeaders.get("foo"));
		assertEquals(Arrays.asList("baz"), actualNativeHeaders.get("bar"));
	}
