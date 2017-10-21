	@Override
	public void printFailuresTo(PrintWriter writer) {
		if (getTotalFailureCount() > 0) {
			writer.println();
			writer.println(String.format("Failures (%d):", getTotalFailureCount()));
			this.failures.forEach(failure -> {
				writer.println(TAB + describeTest(failure.getTestIdentifier()));
				failure.getTestIdentifier().getSource().ifPresent(source -> writer.println(DOUBLE_TAB + source));
				writer.println(String.format("%s=> %s", DOUBLE_TAB, failure.getException()));
			});
			writer.flush();
		}
	}
