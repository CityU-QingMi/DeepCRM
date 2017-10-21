	@Test
	void exceptionInLeafExecute() throws Exception {

		MyLeaf child = spy(new MyLeaf(UniqueId.root("leaf", "leaf")));
		RuntimeException anException = new RuntimeException("in test");
		when(child.execute(eq(rootContext), any())).thenThrow(anException);
		root.addChild(child);

		InOrder inOrder = inOrder(listener, root, child);

		executor.execute();

		ArgumentCaptor<TestExecutionResult> childExecutionResult = ArgumentCaptor.forClass(TestExecutionResult.class);
		inOrder.verify(listener).executionStarted(root);
		inOrder.verify(root).before(rootContext);
		inOrder.verify(listener).executionStarted(child);
		inOrder.verify(child).execute(eq(rootContext), any());
		inOrder.verify(listener).executionFinished(eq(child), childExecutionResult.capture());
		inOrder.verify(root).after(rootContext);
		inOrder.verify(listener).executionFinished(eq(root), any(TestExecutionResult.class));

		assertTrue(childExecutionResult.getValue().getStatus() == TestExecutionResult.Status.FAILED,
			"Execution of child should fail.");
		assertSame(childExecutionResult.getValue().getThrowable().get(), anException);
	}
