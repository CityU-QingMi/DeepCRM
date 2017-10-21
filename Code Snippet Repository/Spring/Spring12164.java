	private boolean isSingleValueMap(MethodParameter parameter) {
		if (!MultiValueMap.class.isAssignableFrom(parameter.getParameterType())) {
			ResolvableType[] genericTypes = ResolvableType.forMethodParameter(parameter).getGenerics();
			if (genericTypes.length == 2) {
				Class<?> declaredClass = genericTypes[1].getRawClass();
				return (declaredClass == null || !List.class.isAssignableFrom(declaredClass));
			}
		}
		return false;
	}
