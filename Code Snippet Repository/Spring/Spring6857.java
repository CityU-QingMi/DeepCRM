	@Nullable
	protected Class<?> getSerializationView(@Nullable Object conversionHint) {
		if (conversionHint instanceof MethodParameter) {
			MethodParameter methodParam = (MethodParameter) conversionHint;
			JsonView annotation = methodParam.getParameterAnnotation(JsonView.class);
			if (annotation == null) {
				annotation = methodParam.getMethodAnnotation(JsonView.class);
				if (annotation == null) {
					return null;
				}
			}
			return extractViewClass(annotation, conversionHint);
		}
		else if (conversionHint instanceof JsonView) {
			return extractViewClass((JsonView) conversionHint, conversionHint);
		}
		else if (conversionHint instanceof Class) {
			return (Class<?>) conversionHint;
		}
		else {
			return null;
		}
	}
