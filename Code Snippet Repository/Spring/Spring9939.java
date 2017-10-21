	private void recordCapturedVariable(int pos, String variableName) {
		if (this.capturedVariableNames == null) {
			this.capturedVariableNames = new ArrayList<>();
		}
		if (this.capturedVariableNames.contains(variableName)) {
			throw new PatternParseException(pos, this.pathPatternData,
					PatternMessage.ILLEGAL_DOUBLE_CAPTURE, variableName);
		}
		this.capturedVariableNames.add(variableName);
	}
