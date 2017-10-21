	protected <A extends Annotation> ConditionEvaluationResult evaluateAnnotation(Class<A> annotationType,
			Function<A, String> expressionExtractor, Function<A, String> reasonExtractor,
			Function<A, Boolean> loadContextExtractor, boolean enabledOnTrue, ExtensionContext context) {

		Assert.state(context.getElement().isPresent(), "No AnnotatedElement");
		AnnotatedElement element = context.getElement().get();
		Optional<A> annotation = findMergedAnnotation(element, annotationType);

		if (!annotation.isPresent()) {
			String reason = String.format("%s is enabled since @%s is not present", element,
					annotationType.getSimpleName());
			if (logger.isDebugEnabled()) {
				logger.debug(reason);
			}
			return ConditionEvaluationResult.enabled(reason);
		}

		String expression = annotation.map(expressionExtractor).map(String::trim).filter(StringUtils::hasLength)
				.orElseThrow(() -> new IllegalStateException(String.format(
						"The expression in @%s on [%s] must not be blank", annotationType.getSimpleName(), element)));

		boolean loadContext = loadContextExtractor.apply(annotation.get());
		boolean evaluatedToTrue = evaluateExpression(expression, loadContext, annotationType, context);

		if (evaluatedToTrue) {
			String adjective = (enabledOnTrue ? "enabled" : "disabled");
			String reason = annotation.map(reasonExtractor).filter(StringUtils::hasText).orElseGet(
					() -> String.format("%s is %s because @%s(\"%s\") evaluated to true", element, adjective,
						annotationType.getSimpleName(), expression));
			if (logger.isInfoEnabled()) {
				logger.info(reason);
			}
			return (enabledOnTrue ? ConditionEvaluationResult.enabled(reason)
					: ConditionEvaluationResult.disabled(reason));
		}
		else {
			String adjective = (enabledOnTrue ? "disabled" : "enabled");
			String reason = String.format("%s is %s because @%s(\"%s\") did not evaluate to true",
					element, adjective, annotationType.getSimpleName(), expression);
			if (logger.isDebugEnabled()) {
				logger.debug(reason);
			}
			return (enabledOnTrue ? ConditionEvaluationResult.disabled(reason) :
					ConditionEvaluationResult.enabled(reason));
		}
	}
