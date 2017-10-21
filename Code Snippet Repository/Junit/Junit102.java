	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
		Optional<AnnotatedElement> element = context.getElement();
		Optional<Disabled> disabled = findAnnotation(element, Disabled.class);
		if (disabled.isPresent()) {
			String reason = disabled.map(Disabled::value).filter(StringUtils::isNotBlank).orElseGet(
				() -> element.get() + " is @Disabled");
			return ConditionEvaluationResult.disabled(reason);
		}

		return ENABLED;
	}
