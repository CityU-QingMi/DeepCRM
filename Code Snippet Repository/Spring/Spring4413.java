	@SuppressWarnings("")
	public static <A extends Annotation> A synthesizeAnnotation(Map<String, Object> attributes,
			Class<A> annotationType, @Nullable AnnotatedElement annotatedElement) {

		Assert.notNull(attributes, "'attributes' must not be null");
		Assert.notNull(annotationType, "'annotationType' must not be null");

		MapAnnotationAttributeExtractor attributeExtractor =
				new MapAnnotationAttributeExtractor(attributes, annotationType, annotatedElement);
		InvocationHandler handler = new SynthesizedAnnotationInvocationHandler(attributeExtractor);
		Class<?>[] exposedInterfaces = (canExposeSynthesizedMarker(annotationType) ?
				new Class<?>[] {annotationType, SynthesizedAnnotation.class} : new Class<?>[] {annotationType});
		return (A) Proxy.newProxyInstance(annotationType.getClassLoader(), exposedInterfaces, handler);
	}
