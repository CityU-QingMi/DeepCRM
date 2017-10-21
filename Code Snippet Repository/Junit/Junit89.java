	public ConditionEvaluationResult evaluate(ExtensionRegistry extensionRegistry,
			ConfigurationParameters configurationParameters, ExtensionContext context) {

		// @formatter:off
		return extensionRegistry.stream(ExecutionCondition.class)
				.filter(conditionIsActivated(configurationParameters))
				.map(condition -> evaluate(condition, context))
				.filter(ConditionEvaluationResult::isDisabled)
				.findFirst()
				.orElse(ENABLED);
		// @formatter:on
	}
