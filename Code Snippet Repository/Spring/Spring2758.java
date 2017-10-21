	public static Advisor advisor() {
		return new DefaultPointcutAdvisor(
			new StaticMethodMatcherPointcut() {
				@Override
				public boolean matches(Method method, Class<?> targetClass) {
					return method.getParameterCount() == 1 &&
						method.getParameterTypes()[0].equals(Integer.class);
				}
			},
			new TraceAfterReturningAdvice());
	}
