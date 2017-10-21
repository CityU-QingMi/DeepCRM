	@Test
	public void addNativeHeaderImmutable() {
		NativeMessageHeaderAccessor headerAccessor = new NativeMessageHeaderAccessor();
		headerAccessor.addNativeHeader("foo", "bar");
		headerAccessor.setImmutable();

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Already immutable");
		headerAccessor.addNativeHeader("foo", "baz");
	}
