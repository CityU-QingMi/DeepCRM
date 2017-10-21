	@Test
	public void resolveEmbeddedValue() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		StringValueResolver r1 = mock(StringValueResolver.class);
		StringValueResolver r2 = mock(StringValueResolver.class);
		StringValueResolver r3 = mock(StringValueResolver.class);
		bf.addEmbeddedValueResolver(r1);
		bf.addEmbeddedValueResolver(r2);
		bf.addEmbeddedValueResolver(r3);
		given(r1.resolveStringValue("A")).willReturn("B");
		given(r2.resolveStringValue("B")).willReturn(null);
		given(r3.resolveStringValue(isNull())).willThrow(new IllegalArgumentException());

		bf.resolveEmbeddedValue("A");

		verify(r1).resolveStringValue("A");
		verify(r2).resolveStringValue("B");
		verify(r3, never()).resolveStringValue(isNull());
	}
