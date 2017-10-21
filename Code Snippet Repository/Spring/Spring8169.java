	@Override
	protected void runChild(FrameworkMethod frameworkMethod, RunNotifier notifier) {
		Description description = describeChild(frameworkMethod);
		if (isTestMethodIgnored(frameworkMethod)) {
			notifier.fireTestIgnored(description);
		}
		else {
			Statement statement;
			try {
				statement = methodBlock(frameworkMethod);
			}
			catch (Throwable ex) {
				statement = new Fail(ex);
			}
			runLeaf(statement, description, notifier);
		}
	}
