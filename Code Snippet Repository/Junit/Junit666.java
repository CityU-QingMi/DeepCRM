	private void writeSkippedOrErrorOrFailureElement(TestIdentifier testIdentifier, XMLStreamWriter writer)
			throws XMLStreamException {

		if (this.reportData.wasSkipped(testIdentifier)) {
			writeSkippedElement(this.reportData.getSkipReason(testIdentifier), writer);
		}
		else {
			Optional<TestExecutionResult> result = this.reportData.getResult(testIdentifier);
			if (result.isPresent() && result.get().getStatus() == FAILED) {
				writeErrorOrFailureElement(result.get().getThrowable(), writer);
			}
		}
	}
