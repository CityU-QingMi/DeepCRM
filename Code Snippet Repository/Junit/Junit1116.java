		@Test
		void supportsDynamicTestRegistration() throws Exception {
			RunListener runListener = mock(RunListener.class);
			RunNotifier notifier = new RunNotifier();
			// notifier.addListener(new LoggingRunListener());
			notifier.addListener(runListener);
			new JUnitPlatform(TestClass.class, createLauncher(new DynamicTestEngine())).run(notifier);

			InOrder inOrder = inOrder(runListener);

			inOrder.verify(runListener).testStarted(testDescription("[engine:dynamic]/[container:1]/[test:1]"));
			inOrder.verify(runListener).testFinished(testDescription("[engine:dynamic]/[container:1]/[test:1]"));

			inOrder.verify(runListener).testStarted(testDescription("[engine:dynamic]/[container:1]/[test:2]"));
			inOrder.verify(runListener).testFinished(testDescription("[engine:dynamic]/[container:1]/[test:2]"));

			inOrder.verify(runListener).testStarted(testDescription("[engine:dynamic]/[container:1]/[test:3]"));
			inOrder.verify(runListener).testFinished(testDescription("[engine:dynamic]/[container:1]/[test:3]"));

			inOrder.verify(runListener).testStarted(
				testDescription("[engine:dynamic]/[container:1]/[test:3]/[test:3a]"));
			inOrder.verify(runListener).testFinished(
				testDescription("[engine:dynamic]/[container:1]/[test:3]/[test:3a]"));

			inOrder.verifyNoMoreInteractions();
		}
