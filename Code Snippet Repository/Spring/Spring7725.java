	@Test
	public void addNativeHeader() {
		MultiValueMap<String, String> nativeHeaders = new LinkedMultiValueMap<>();
		nativeHeaders.add("foo", "bar");

		NativeMessageHeaderAccessor headers = new NativeMessageHeaderAccessor(nativeHeaders);
		headers.addNativeHeader("foo", "baz");

		assertEquals(Arrays.asList("bar", "baz"), headers.getNativeHeader("foo"));
	}
