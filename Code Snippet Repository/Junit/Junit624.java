	private static boolean hasCompatibleSignature(Method candidate, String methodName, Class<?>[] parameterTypes) {
		if (!methodName.equals(candidate.getName())) {
			return false;
		}
		if (parameterTypes.length != candidate.getParameterCount()) {
			return false;
		}
		// trivial case: parameter types exactly match
		if (Arrays.equals(parameterTypes, candidate.getParameterTypes())) {
			return true;
		}
		// param count is equal, but types do not match exactly: check for method sub-signatures
		// https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.2
		for (int i = 0; i < parameterTypes.length; i++) {
			Class<?> lowerType = parameterTypes[i];
			Class<?> upperType = candidate.getParameterTypes()[i];
			if (!upperType.isAssignableFrom(lowerType)) {
				return false;
			}
		}
		// lower is sub-signature of upper: check for generics in upper method
		if (isGeneric(candidate)) {
			return true;
		}
		return false;
	}
