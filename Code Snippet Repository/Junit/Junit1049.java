	@Test
	void exceptionInShouldBeSkipped() throws Exception {

		MyContainer child = spy(new MyContainer(UniqueId.root("container", "child container")));
		RuntimeException anException = new RuntimeException("in skip");
		when(child.shouldBeSkipped(rootContext)).thenThrow(anException);
		root.addChild(child);

		InOrder inOrder = inOrder(listener, child);

		executor.execute();

		ArgumentCaptor<TestExecutionResult> childExecutionResult = ArgumentCaptor.forClass(TestExecutionResult.class);
		inOrder.verify(listener).executionStarted(root);
		inOrder.verify(child).prepare(rootContext);
		inOrder.verify(child).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(child);
		inOrder.verify(listener).executionFinished(eq(child), childExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(root), any(TestExecutionResult.class));

		verifyNoMoreInteractions(child);

		assertTrue(childExecutionResult.getValue().getStatus() == TestExecutionResult.Status.FAILED,
			"Execution of child should fail.");
		assertSame(childExecutionResult.getValue().getThrowable().get(), anException);
	}
