		public StringSetterNullReplacementAdvice() {
			super(cleaner);
			setPointcut(new DynamicMethodMatcherPointcut() {
				@Override
				public boolean matches(Method m, @Nullable Class<?> targetClass, Object... args) {
					return args[0] == null;
				}
				@Override
				public boolean matches(Method m, @Nullable Class<?> targetClass) {
					return m.getName().startsWith("set") &&
						m.getParameterCount() == 1 &&
						m.getParameterTypes()[0].equals(String.class);
				}
			});
		}
