	private boolean shouldHandle(ApplicationEvent event, @Nullable Object[] args) {
		if (args == null) {
			return false;
		}
		String condition = getCondition();
		if (StringUtils.hasText(condition)) {
			Assert.notNull(this.evaluator, "EventExpressionEvaluator must no be null");
			EvaluationContext evaluationContext = this.evaluator.createEvaluationContext(
					event, this.targetClass, this.method, args, this.applicationContext);
			return this.evaluator.condition(condition, this.methodKey, evaluationContext);
		}
		return true;
	}
