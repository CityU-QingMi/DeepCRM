	@Override
	public void evaluate() throws Throwable {
		if (this.testMethod == null) {
			if (!ProfileValueUtils.isTestEnabledInThisEnvironment(this.testClass)) {
				Annotation ann = AnnotatedElementUtils.findMergedAnnotation(this.testClass, IfProfileValue.class);
				throw new AssumptionViolatedException(String.format(
						"Profile configured via [%s] is not enabled in this environment for test class [%s].",
						ann, this.testClass.getName()));
			}
		}
		else {
			if (!ProfileValueUtils.isTestEnabledInThisEnvironment(this.testMethod, this.testClass)) {
				throw new AssumptionViolatedException(String.format(
						"Profile configured via @IfProfileValue is not enabled in this environment for test method [%s].",
						this.testMethod));
			}
		}

		this.next.evaluate();
	}
