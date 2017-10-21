	protected String generateDefaultCacheName(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		List<String> parameters = new ArrayList<>(parameterTypes.length);
		for (Class<?> parameterType : parameterTypes) {
			parameters.add(parameterType.getName());
		}

		StringBuilder sb = new StringBuilder(method.getDeclaringClass().getName());
		sb.append(".").append(method.getName());
		sb.append("(").append(StringUtils.collectionToCommaDelimitedString(parameters)).append(")");
		return sb.toString();
	}
