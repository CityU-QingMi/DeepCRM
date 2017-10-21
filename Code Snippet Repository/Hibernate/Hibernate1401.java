	private ValueGeneration getValueGenerationFromAnnotations(XProperty property) {
		AnnotationValueGeneration<?> valueGeneration = null;

		for ( Annotation annotation : property.getAnnotations() ) {
			AnnotationValueGeneration<?> candidate = getValueGenerationFromAnnotation( property, annotation );

			if ( candidate != null ) {
				if ( valueGeneration != null ) {
					throw new AnnotationException(
							"Only one generator annotation is allowed:" + StringHelper.qualify(
									holder.getPath(),
									name
							)
					);
				}
				else {
					valueGeneration = candidate;
				}
			}
		}

		return valueGeneration;
	}
