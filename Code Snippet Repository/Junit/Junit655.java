	@Override
	public void executionStarted(TestIdentifier testIdentifier) {
		this.executionStartedMillis = System.currentTimeMillis();
		if (testIdentifier.isContainer()) {
			printVerticals(theme.entry());
			printf(Color.CONTAINER, " %s", testIdentifier.getDisplayName());
			printf(NONE, "%n");
			frames.push(System.currentTimeMillis());
		}
		if (testIdentifier.isContainer()) {
			return;
		}
		printVerticals(theme.entry());
		printf(Color.valueOf(testIdentifier), " %s%n", testIdentifier.getDisplayName());
		printDetails(testIdentifier);
	}
