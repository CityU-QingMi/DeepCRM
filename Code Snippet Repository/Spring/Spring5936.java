			@Override
			public TypedValue execute(EvaluationContext context, Object target, Object... arguments)
					throws AccessException {
				try {
					Method m = HasRoleExecutor.class.getMethod("hasRole", String[].class);
					Object[] args = arguments;
					if (args != null) {
						ReflectionHelper.convertAllArguments(tc, args, m);
					}
					if (m.isVarArgs()) {
						args = ReflectionHelper.setupArgumentsForVarargsInvocation(m.getParameterTypes(), args);
					}
					return new TypedValue(m.invoke(null, args), new TypeDescriptor(new MethodParameter(m,-1)));
				}
				catch (Exception ex) {
					throw new AccessException("Problem invoking hasRole", ex);
				}
			}
