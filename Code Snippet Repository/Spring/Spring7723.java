	@Test
	public void setNativeHeaderNullValue() {
		MultiValueMap<String, String> nativeHeaders = new LinkedMultiValueMap<>();
		nativeHeaders.add("foo", "bar");

		NativeMessageHeaderAccessor headers = new NativeMessageHeaderAccessor(nativeHeaders);
		headers.setNativeHeader("foo", null);

		assertNull(headers.getNativeHeader("foo"));
	}
