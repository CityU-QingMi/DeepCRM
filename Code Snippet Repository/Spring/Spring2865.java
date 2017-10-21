		public PointcutForVoid() {
			setAdvice(new MethodInterceptor() {
				@Override
				public Object invoke(MethodInvocation invocation) throws Throwable {
					methodNames.add(invocation.getMethod().getName());
					return invocation.proceed();
				}
			});
			setPointcut(new DynamicMethodMatcherPointcut() {
				@Override
				public boolean matches(Method m, @Nullable Class<?> targetClass, Object... args) {
					return m.getReturnType() == Void.TYPE;
				}
			});
		}
