	@Test
	public void addNativeHeaderNullValue() {
		MultiValueMap<String, String> nativeHeaders = new LinkedMultiValueMap<>();
		nativeHeaders.add("foo", "bar");

		NativeMessageHeaderAccessor headers = new NativeMessageHeaderAccessor(nativeHeaders);
		headers.addNativeHeader("foo", null);

		assertEquals(Arrays.asList("bar"), headers.getNativeHeader("foo"));
	}
