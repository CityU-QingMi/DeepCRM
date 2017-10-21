	@Test
	void outOfMemoryErrorInLeafExecution() throws Exception {
		MyLeaf child = spy(new MyLeaf(UniqueId.root("leaf", "leaf")));
		OutOfMemoryError outOfMemoryError = new OutOfMemoryError("in test");
		when(child.execute(eq(rootContext), any())).thenThrow(outOfMemoryError);
		root.addChild(child);

		Throwable actualException = assertThrows(OutOfMemoryError.class, () -> executor.execute());
		assertSame(outOfMemoryError, actualException);
	}
