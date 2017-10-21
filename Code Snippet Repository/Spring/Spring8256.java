		public AnnotationDescriptor(Class<?> rootDeclaringClass, Class<?> declaringClass,
				@Nullable Annotation composedAnnotation, T annotation) {

			Assert.notNull(rootDeclaringClass, "'rootDeclaringClass' must not be null");
			Assert.notNull(annotation, "Annotation must not be null");
			this.rootDeclaringClass = rootDeclaringClass;
			this.declaringClass = declaringClass;
			this.composedAnnotation = composedAnnotation;
			this.annotation = annotation;
			AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(
					rootDeclaringClass, annotation.annotationType().getName(), false, false);
			Assert.state(attributes != null, "No annotation attributes");
			this.annotationAttributes = attributes;
		}
