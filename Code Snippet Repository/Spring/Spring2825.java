		public TestDynamicPointcutForSettersOnly(MethodInterceptor mi, final String pattern) {
			super(mi);
			setPointcut(new DynamicMethodMatcherPointcut() {
				@Override
				public boolean matches(Method m, @Nullable Class<?> targetClass, Object... args) {
					boolean run = m.getName().contains(pattern);
					if (run) ++count;
					return run;
				}
				@Override
				public boolean matches(Method m, @Nullable Class<?> clazz) {
					return m.getName().startsWith("set");
				}
			});
		}
