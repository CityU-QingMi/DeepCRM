	@Override
	@Nullable
	public String[] getParameterNames(Constructor<?> ctor) {
		if (!KotlinDetector.isKotlinType(ctor.getDeclaringClass())) {
			return null;
		}

		try {
			KFunction<?> function = ReflectJvmMapping.getKotlinFunction(ctor);
			return (function != null ? getParameterNames(function.getParameters()) : null);
		}
		catch (UnsupportedOperationException ex) {
			return null;
		}
	}
