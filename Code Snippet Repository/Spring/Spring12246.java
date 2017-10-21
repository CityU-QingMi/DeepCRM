	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		if (StreamingResponseBody.class.isAssignableFrom(returnType.getParameterType())) {
			return true;
		}
		else if (ResponseEntity.class.isAssignableFrom(returnType.getParameterType())) {
			Class<?> bodyType = ResolvableType.forMethodParameter(returnType).getGeneric(0).resolve();
			return (bodyType != null && StreamingResponseBody.class.isAssignableFrom(bodyType));
		}
		return false;
	}
