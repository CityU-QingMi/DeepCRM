	@Override
	public boolean supportsReturnType(MethodParameter returnType) {

		Class<?> bodyType = ResponseEntity.class.isAssignableFrom(returnType.getParameterType()) ?
				ResolvableType.forMethodParameter(returnType).getGeneric(0).resolve() :
				returnType.getParameterType();

		return bodyType != null && (ResponseBodyEmitter.class.isAssignableFrom(bodyType) ||
				this.reactiveHandler.isReactiveType(bodyType));
	}
