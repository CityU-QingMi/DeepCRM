	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (ReflectionUtils.isEqualsMethod(method)) {
			return annotationEquals(args[0]);
		}
		if (ReflectionUtils.isHashCodeMethod(method)) {
			return annotationHashCode();
		}
		if (ReflectionUtils.isToStringMethod(method)) {
			return annotationToString();
		}
		if (AnnotationUtils.isAnnotationTypeMethod(method)) {
			return annotationType();
		}
		if (!AnnotationUtils.isAttributeMethod(method)) {
			throw new AnnotationConfigurationException(String.format(
					"Method [%s] is unsupported for synthesized annotation type [%s]", method, annotationType()));
		}
		return getAttributeValue(method);
	}
