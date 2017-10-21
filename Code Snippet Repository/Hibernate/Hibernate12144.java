	private void handleRootElementAnnotationMirrors(final Element element) {
		List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
		for ( AnnotationMirror mirror : annotationMirrors ) {
			if ( !ElementKind.CLASS.equals( element.getKind() ) ) {
				continue;
			}

			String fqn = ( (TypeElement) element ).getQualifiedName().toString();
			MetaEntity alreadyExistingMetaEntity = tryGettingExistingEntityFromContext( mirror, fqn );
			if ( alreadyExistingMetaEntity != null && alreadyExistingMetaEntity.isMetaComplete() ) {
				String msg = "Skipping processing of annotations for " + fqn + " since xml configuration is metadata complete.";
				context.logMessage( Diagnostic.Kind.OTHER, msg );
				continue;
			}

			boolean requiresLazyMemberInitialization = false;
			AnnotationMetaEntity metaEntity;
			if ( TypeUtils.containsAnnotation( element, Constants.EMBEDDABLE ) ||
					TypeUtils.containsAnnotation( element, Constants.MAPPED_SUPERCLASS ) ) {
				requiresLazyMemberInitialization = true;
			}

			metaEntity = new AnnotationMetaEntity( (TypeElement) element, context, requiresLazyMemberInitialization );

			if ( alreadyExistingMetaEntity != null ) {
				metaEntity.mergeInMembers( alreadyExistingMetaEntity );
			}
			addMetaEntityToContext( mirror, metaEntity );
		}
	}
