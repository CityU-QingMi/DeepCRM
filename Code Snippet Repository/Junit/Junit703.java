	@Override
	public FilterResult apply(TestEngine testEngine) {
		Preconditions.notNull(testEngine, "TestEngine must not be null");
		String engineId = testEngine.getId();
		Preconditions.notBlank(engineId, "TestEngine ID must not be null or blank");

		if (this.type == Type.INCLUDE) {
			return includedIf(this.engineIds.stream().anyMatch(engineId::equals), //
				() -> String.format("Engine ID [%s] is in included list [%s]", engineId, this.engineIds), //
				() -> String.format("Engine ID [%s] is not in included list [%s]", engineId, this.engineIds));
		}
		else {
			return includedIf(this.engineIds.stream().noneMatch(engineId::equals), //
				() -> String.format("Engine ID [%s] is not in excluded list [%s]", engineId, this.engineIds), //
				() -> String.format("Engine ID [%s] is in excluded list [%s]", engineId, this.engineIds));
		}
	}
