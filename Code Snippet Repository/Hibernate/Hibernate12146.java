	private void addMetaEntityToContext(AnnotationMirror mirror, AnnotationMetaEntity metaEntity) {
		if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.ENTITY ) ) {
			context.addMetaEntity( metaEntity.getQualifiedName(), metaEntity );
		}
		else if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.MAPPED_SUPERCLASS ) ) {
			context.addMetaEntity( metaEntity.getQualifiedName(), metaEntity );
		}
		else if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.EMBEDDABLE ) ) {
			context.addMetaEmbeddable( metaEntity.getQualifiedName(), metaEntity );
		}
	}
