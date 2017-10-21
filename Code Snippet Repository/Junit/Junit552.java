	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
		Optional<SystemProperty> optional = findAnnotation(context.getElement(), SystemProperty.class);

		if (optional.isPresent()) {
			SystemProperty systemProperty = optional.get();
			String key = systemProperty.key();
			String expected = systemProperty.value();
			String actual = System.getProperty(key);

			if (!Objects.equals(expected, actual)) {
				return ConditionEvaluationResult.disabled(
					String.format("System property [%s] has a value of [%s] instead of [%s]", key, actual, expected));
			}
		}

		return ConditionEvaluationResult.enabled("@SystemProperty is not present");
	}
