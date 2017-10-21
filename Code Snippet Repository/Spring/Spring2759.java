	public static Advisor advisor() {
		return new DefaultPointcutAdvisor(
			new StaticMethodMatcherPointcut() {
				@Override
				public boolean matches(Method method, Class<?> targetClass) {
					return method.getReturnType().equals(String.class);
				}
			},
			new TraceBeforeAdvice());
	}
