	@Test
	void exceptionInContainerAfterAll() throws Exception {

		MyLeaf child = spy(new MyLeaf(UniqueId.root("leaf", "child container")));
		root.addChild(child);
		RuntimeException anException = new RuntimeException("in test");
		doThrow(anException).when(root).after(rootContext);

		InOrder inOrder = inOrder(listener, root, child);

		executor.execute();

		ArgumentCaptor<TestExecutionResult> rootExecutionResult = ArgumentCaptor.forClass(TestExecutionResult.class);
		inOrder.verify(root).prepare(rootContext);
		inOrder.verify(root).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(root);
		inOrder.verify(root).before(rootContext);
		inOrder.verify(listener).executionStarted(child);
		inOrder.verify(child).execute(eq(rootContext), any());
		inOrder.verify(listener).executionFinished(eq(child), any(TestExecutionResult.class));
		inOrder.verify(root).after(rootContext);
		inOrder.verify(listener).executionFinished(eq(root), rootExecutionResult.capture());

		assertTrue(rootExecutionResult.getValue().getStatus() == TestExecutionResult.Status.FAILED,
			"Execution of root should fail.");
		assertSame(rootExecutionResult.getValue().getThrowable().get(), anException);
	}
