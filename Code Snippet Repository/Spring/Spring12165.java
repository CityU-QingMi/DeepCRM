	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (!parameter.hasParameterAnnotation(MatrixVariable.class)) {
			return false;
		}
		if (Map.class.isAssignableFrom(parameter.nestedIfOptional().getNestedParameterType())) {
			MatrixVariable matrixVariable = parameter.getParameterAnnotation(MatrixVariable.class);
			return (matrixVariable != null && StringUtils.hasText(matrixVariable.name()));
		}
		return true;
	}
