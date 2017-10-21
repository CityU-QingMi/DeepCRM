	@Override
	@Nullable
	public String[] getParameterNames(Method method) {
		if (!KotlinDetector.isKotlinType(method.getDeclaringClass())) {
			return null;
		}

		try {
			KFunction<?> function = ReflectJvmMapping.getKotlinFunction(method);
			return (function != null ? getParameterNames(function.getParameters()) : null);
		}
		catch (UnsupportedOperationException ex) {
			return null;
		}
	}
