	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		MatrixVariable matrixVariable = parameter.getParameterAnnotation(MatrixVariable.class);
		if (matrixVariable != null) {
			if (Map.class.isAssignableFrom(parameter.getParameterType())) {
				return !StringUtils.hasText(matrixVariable.name());
			}
		}
		return false;
	}
