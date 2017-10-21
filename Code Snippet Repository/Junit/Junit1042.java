	@Test
	void executesDynamicTestDescriptorsUsingContainerAndTestType() throws Exception {

		MyContainerAndTest child = spy(new MyContainerAndTest(root.getUniqueId().append("c&t", "child")));
		MyContainerAndTest dynamicContainerAndTest = spy(
			new MyContainerAndTest(child.getUniqueId().append("c&t", "dynamicContainerAndTest")));
		MyLeaf dynamicLeaf = spy(new MyLeaf(dynamicContainerAndTest.getUniqueId().append("test", "dynamicLeaf")));

		root.addChild(child);
		when(child.execute(any(), any())).thenAnswer(registerAndExecute(dynamicContainerAndTest));
		when(dynamicContainerAndTest.execute(any(), any())).thenAnswer(registerAndExecute(dynamicLeaf));
		when(dynamicLeaf.execute(any(), any())).thenAnswer(invocation -> {
			throw new AssertionError("test fails");
		});

		InOrder inOrder = inOrder(listener, root, child, dynamicContainerAndTest, dynamicLeaf);

		executor.execute();

		ArgumentCaptor<TestExecutionResult> aTestExecutionResult = ArgumentCaptor.forClass(TestExecutionResult.class);
		inOrder.verify(listener).executionStarted(root);

		inOrder.verify(child).prepare(rootContext);
		inOrder.verify(child).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(child);
		inOrder.verify(child).execute(eq(rootContext), any());

		inOrder.verify(listener).dynamicTestRegistered(dynamicContainerAndTest);
		inOrder.verify(dynamicContainerAndTest).prepare(rootContext);
		inOrder.verify(dynamicContainerAndTest).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(dynamicContainerAndTest);
		inOrder.verify(dynamicContainerAndTest).execute(eq(rootContext), any());

		inOrder.verify(listener).dynamicTestRegistered(dynamicLeaf);
		inOrder.verify(dynamicLeaf).prepare(rootContext);
		inOrder.verify(dynamicLeaf).shouldBeSkipped(rootContext);
		inOrder.verify(listener).executionStarted(dynamicLeaf);
		inOrder.verify(dynamicLeaf).execute(eq(rootContext), any());

		inOrder.verify(listener).executionFinished(eq(dynamicLeaf), aTestExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(dynamicContainerAndTest), aTestExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(child), aTestExecutionResult.capture());
		inOrder.verify(listener).executionFinished(eq(root), any(TestExecutionResult.class));

		assertThat(aTestExecutionResult.getAllValues()).extracting(TestExecutionResult::getStatus).containsExactly(
			TestExecutionResult.Status.FAILED, TestExecutionResult.Status.SUCCESSFUL,
			TestExecutionResult.Status.SUCCESSFUL);
	}
