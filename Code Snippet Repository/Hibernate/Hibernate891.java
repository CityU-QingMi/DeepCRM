	private EmbeddableSource interpretIdClass(
			MappingDocument mappingDocument,
			JaxbHbmCompositeIdType jaxbHbmCompositeIdMapping) {
		// if <composite-id/> is null here we have much bigger problems :)

		if ( !jaxbHbmCompositeIdMapping.isMapped() ) {
			return null;
		}

		final String className = jaxbHbmCompositeIdMapping.getClazz();
		if ( StringHelper.isEmpty( className ) ) {
			return null;
		}

		final String idClassQualifiedName = mappingDocument.qualifyClassName( className );
		final JavaTypeDescriptor idClassTypeDescriptor = new JavaTypeDescriptor() {
			@Override
			public String getName() {
				return idClassQualifiedName;
			}
		};
		return new IdClassSource( idClassTypeDescriptor, rootEntitySource, mappingDocument );
	}
