	private <A extends Annotation> AnnotationValueGeneration<A> getValueGenerationFromAnnotation(
			XProperty property,
			A annotation) {
		ValueGenerationType generatorAnnotation = annotation.annotationType()
				.getAnnotation( ValueGenerationType.class );

		if ( generatorAnnotation == null ) {
			return null;
		}

		Class<? extends AnnotationValueGeneration<?>> generationType = generatorAnnotation.generatedBy();
		AnnotationValueGeneration<A> valueGeneration = instantiateAndInitializeValueGeneration(
				annotation, generationType, property
		);

		if ( annotation.annotationType() == Generated.class &&
				property.isAnnotationPresent( javax.persistence.Version.class ) &&
				valueGeneration.getGenerationTiming() == GenerationTiming.INSERT ) {

			throw new AnnotationException(
					"@Generated(INSERT) on a @Version property not allowed, use ALWAYS (or NEVER): "
							+ StringHelper.qualify( holder.getPath(), name )
			);
		}

		return valueGeneration;
	}
