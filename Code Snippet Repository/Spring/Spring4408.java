	public static void registerDefaultValues(AnnotationAttributes attributes) {
		// Only do defaults scanning for public annotations; we'd run into
		// IllegalAccessExceptions otherwise, and we don't want to mess with
		// accessibility in a SecurityManager environment.
		Class<? extends Annotation> annotationType = attributes.annotationType();
		if (annotationType != null && Modifier.isPublic(annotationType.getModifiers())) {
			// Check declared default values of attributes in the annotation type.
			for (Method annotationAttribute : getAttributeMethods(annotationType)) {
				String attributeName = annotationAttribute.getName();
				Object defaultValue = annotationAttribute.getDefaultValue();
				if (defaultValue != null && !attributes.containsKey(attributeName)) {
					if (defaultValue instanceof Annotation) {
						defaultValue = getAnnotationAttributes((Annotation) defaultValue, false, true);
					}
					else if (defaultValue instanceof Annotation[]) {
						Annotation[] realAnnotations = (Annotation[]) defaultValue;
						AnnotationAttributes[] mappedAnnotations = new AnnotationAttributes[realAnnotations.length];
						for (int i = 0; i < realAnnotations.length; i++) {
							mappedAnnotations[i] = getAnnotationAttributes(realAnnotations[i], false, true);
						}
						defaultValue = mappedAnnotations;
					}
					attributes.put(attributeName, new DefaultValueHolder(defaultValue));
				}
			}
		}
	}
