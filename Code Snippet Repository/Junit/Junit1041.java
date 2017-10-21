	@Test
	void executesDynamicTestDescriptors() throws Exception {

		UniqueId leafUniqueId = UniqueId.root("leaf", "child leaf");
		MyLeaf child = spy(new MyLeaf(leafUniqueId));
		MyLeaf dynamicTestDescriptor = spy(new MyLeaf(leafUniqueId.append("dynamic", "child")));

		when(child.execute(any(), any())).thenAnswer(invocation -> {
			DynamicTestExecutor dynamicTestExecutor = invocation.getArgument(1);
			dynamicTestExecutor.execute(dynamicTestDescriptor);
			return invocation.getArgument(0);
		});
		root.addChild(child);

		InOrder inOrder = inOrder(listener, root, child, dynamicTestDescriptor);

		executor.execute();

		ArgumentCaptor<TestExecutionResult> aTestExecutionResult = ArgumentCaptor.forClass(TestExecutionResult.class);
		inOrder.verify(listener).executionStarted(root);
		inOrder.verify(child).prepare(rootContext);
		inOrder.verify(child).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(child);
		inOrder.verify(child).execute(eq(rootContext), any());
		inOrder.verify(listener).dynamicTestRegistered(dynamicTestDescriptor);
		inOrder.verify(dynamicTestDescriptor).prepare(rootContext);
		inOrder.verify(dynamicTestDescriptor).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(dynamicTestDescriptor);
		inOrder.verify(dynamicTestDescriptor).execute(eq(rootContext), any());
		inOrder.verify(listener).executionFinished(eq(dynamicTestDescriptor), aTestExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(child), aTestExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(root), any(TestExecutionResult.class));

		assertThat(aTestExecutionResult.getAllValues()).extracting(TestExecutionResult::getStatus).containsExactly(
			TestExecutionResult.Status.SUCCESSFUL, TestExecutionResult.Status.SUCCESSFUL);
	}
