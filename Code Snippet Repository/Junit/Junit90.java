	private ConditionEvaluationResult evaluate(ExecutionCondition condition, ExtensionContext context) {
		try {
			ConditionEvaluationResult result = condition.evaluateExecutionCondition(context);
			logResult(condition.getClass(), result);
			return result;
		}
		catch (Exception ex) {
			throw evaluationException(condition.getClass(), ex);
		}
	}
