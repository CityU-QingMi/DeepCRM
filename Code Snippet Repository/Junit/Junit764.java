	private Deque<Description> determinePath(RunnerTestDescriptor runnerTestDescriptor,
			Optional<? extends TestDescriptor> identifiedTestDescriptor) {
		Deque<Description> path = new ArrayDeque<>();
		Optional<? extends TestDescriptor> current = identifiedTestDescriptor;
		while (current.isPresent() && !current.get().equals(runnerTestDescriptor)) {
			path.addFirst(((VintageTestDescriptor) current.get()).getDescription());
			current = current.get().getParent();
		}
		return path;
	}
