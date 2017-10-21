	@Test
	void rootDescriptorWithOneChildContainer() throws Exception {

		MyContainer child = spy(new MyContainer(UniqueId.root("container", "child container")));
		root.addChild(child);

		InOrder inOrder = inOrder(listener, root, child);

		executor.execute();

		ArgumentCaptor<TestExecutionResult> childExecutionResult = ArgumentCaptor.forClass(TestExecutionResult.class);
		inOrder.verify(listener).executionStarted(root);
		inOrder.verify(child).prepare(rootContext);
		inOrder.verify(child).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(child);
		inOrder.verify(child).before(rootContext);
		inOrder.verify(child).after(rootContext);
		inOrder.verify(listener).executionFinished(eq(child), childExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(root), any(TestExecutionResult.class));

		assertTrue(childExecutionResult.getValue().getStatus() == TestExecutionResult.Status.SUCCESSFUL,
			"Execution of child container should be successful.");
	}
