	private void validateIfApplicable(WebExchangeDataBinder binder, MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation ann : annotations) {
			Validated validAnnot = AnnotationUtils.getAnnotation(ann, Validated.class);
			if (validAnnot != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
				Object hints = (validAnnot != null ? validAnnot.value() : AnnotationUtils.getValue(ann));
				Object hintArray = (hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
				binder.validate(hintArray);
			}
		}
	}
