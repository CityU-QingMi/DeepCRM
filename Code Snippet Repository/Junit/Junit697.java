	private void execute(TestDescriptor testDescriptor, C parentContext, ExecutionTracker tracker) {
		Node<C> node = asNode(testDescriptor);
		tracker.markExecuted(testDescriptor);

		C preparedContext;
		try {
			preparedContext = node.prepare(parentContext);
			SkipResult skipResult = node.shouldBeSkipped(preparedContext);
			if (skipResult.isSkipped()) {
				this.listener.executionSkipped(testDescriptor, skipResult.getReason().orElse("<unknown>"));
				return;
			}
		}
		catch (Throwable throwable) {
			rethrowIfBlacklisted(throwable);
			// We call executionStarted first to comply with the contract of EngineExecutionListener
			this.listener.executionStarted(testDescriptor);
			this.listener.executionFinished(testDescriptor, TestExecutionResult.failed(throwable));
			return;
		}

		this.listener.executionStarted(testDescriptor);

		TestExecutionResult result = singleTestExecutor.executeSafely(() -> {
			C context = preparedContext;
			try {
				context = node.before(context);

				C contextForDynamicChildren = context;
				context = node.execute(context, dynamicTestDescriptor -> {
					this.listener.dynamicTestRegistered(dynamicTestDescriptor);
					execute(dynamicTestDescriptor, contextForDynamicChildren, tracker);
				});

				C contextForStaticChildren = context;
				// @formatter:off
				testDescriptor.getChildren().stream()
						.filter(child -> !tracker.wasAlreadyExecuted(child))
						.forEach(child -> execute(child, contextForStaticChildren, tracker));
				// @formatter:on
			}
			finally {
				node.after(context);
			}
		});

		this.listener.executionFinished(testDescriptor, result);
	}
