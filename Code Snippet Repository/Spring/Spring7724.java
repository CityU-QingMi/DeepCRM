	@Test
	public void setNativeHeaderImmutable() {
		NativeMessageHeaderAccessor headerAccessor = new NativeMessageHeaderAccessor();
		headerAccessor.setNativeHeader("foo", "bar");
		headerAccessor.setImmutable();

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Already immutable");
		headerAccessor.setNativeHeader("foo", "baz");
	}
