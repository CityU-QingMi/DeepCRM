		@Override
		public void execute(ExecutionRequest request) {
			EngineExecutionListener engineExecutionListener = request.getEngineExecutionListener();
			TestDescriptor root = request.getRootTestDescriptor();

			TestDescriptor container = new DemoContainerTestDescriptor(root.getUniqueId().append("container", "1"),
				"container #1");
			root.addChild(container);

			engineExecutionListener.dynamicTestRegistered(container);
			engineExecutionListener.executionStarted(container);

			UniqueId containerUid = container.getUniqueId();

			TestDescriptor dynamicTest1 = new DemoTestTestDescriptor(containerUid.append("test", "1"),
				"dynamic test #1");
			container.addChild(dynamicTest1);
			engineExecutionListener.dynamicTestRegistered(dynamicTest1);
			engineExecutionListener.executionStarted(dynamicTest1);
			engineExecutionListener.executionFinished(dynamicTest1, TestExecutionResult.successful());

			TestDescriptor dynamicTest2 = new DemoTestTestDescriptor(containerUid.append("test", "2"),
				"dynamic test #2");
			container.addChild(dynamicTest2);
			engineExecutionListener.dynamicTestRegistered(dynamicTest2);
			engineExecutionListener.executionStarted(dynamicTest2);
			engineExecutionListener.executionFinished(dynamicTest2, TestExecutionResult.successful());

			TestDescriptor dynamicTest3 = new DemoContainerAndTestTestDescriptor(containerUid.append("test", "3"),
				"dynamic test #3");
			container.addChild(dynamicTest3);
			engineExecutionListener.dynamicTestRegistered(dynamicTest3);
			engineExecutionListener.executionStarted(dynamicTest3);
			engineExecutionListener.executionFinished(dynamicTest3, TestExecutionResult.successful());

			TestDescriptor dynamicTest3a = new DemoTestTestDescriptor(dynamicTest3.getUniqueId().append("test", "3a"),
				"dynamic test #3a");
			dynamicTest3.addChild(dynamicTest3a);
			engineExecutionListener.dynamicTestRegistered(dynamicTest3a);
			engineExecutionListener.executionStarted(dynamicTest3a);
			engineExecutionListener.executionFinished(dynamicTest3a, TestExecutionResult.successful());

			engineExecutionListener.executionFinished(container, TestExecutionResult.successful());
		}
