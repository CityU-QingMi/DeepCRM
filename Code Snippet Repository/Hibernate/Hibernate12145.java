	private MetaEntity tryGettingExistingEntityFromContext(AnnotationMirror mirror, String fqn) {
		MetaEntity alreadyExistingMetaEntity = null;
		if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.ENTITY )
				|| TypeUtils.isAnnotationMirrorOfType( mirror, Constants.MAPPED_SUPERCLASS )) {
			alreadyExistingMetaEntity = context.getMetaEntity( fqn );
		}
		else if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.EMBEDDABLE ) ) {
			alreadyExistingMetaEntity = context.getMetaEmbeddable( fqn );
		}
		return alreadyExistingMetaEntity;
	}
