	public PluralAttributeMapKeySourceEmbeddedImpl(
			MappingDocument mappingDocument,
			AbstractPluralAttributeSourceImpl pluralAttributeSource,
			final JaxbHbmMapKeyCompositeType jaxbCompositeMapKey) {
		this(
				mappingDocument,
				pluralAttributeSource,
				new EmbeddableMapping() {
					@Override
					public String getClazz() {
						return jaxbCompositeMapKey.getClazz();
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
				jaxbCompositeMapKey.getAttributes()
		);
	}
