	@Test
	public void setNativeHeader() {
		MultiValueMap<String, String> nativeHeaders = new LinkedMultiValueMap<>();
		nativeHeaders.add("foo", "bar");

		NativeMessageHeaderAccessor headers = new NativeMessageHeaderAccessor(nativeHeaders);
		headers.setNativeHeader("foo", "baz");

		assertEquals(Arrays.asList("baz"), headers.getNativeHeader("foo"));
	}
