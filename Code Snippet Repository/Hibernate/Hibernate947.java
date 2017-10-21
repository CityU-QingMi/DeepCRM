	public PluralAttributeMapKeySourceEmbeddedImpl(
			MappingDocument mappingDocument,
			AbstractPluralAttributeSourceImpl pluralAttributeSource,
			final JaxbHbmCompositeIndexType jaxbCompositeIndexElement) {
		this(
				mappingDocument,
				pluralAttributeSource,
				new EmbeddableMapping() {
					@Override
					public String getClazz() {
						return jaxbCompositeIndexElement.getClazz();
					}

					@Override
					public List<JaxbHbmTuplizerType> getTuplizer() {
						return Collections.emptyList();
					}

					@Override
					public String getParent() {
						return null;
					}
				},
				jaxbCompositeIndexElement.getAttributes()
		);
	}
