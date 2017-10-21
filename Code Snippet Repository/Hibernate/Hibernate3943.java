	public void setTypeName(String typeName) {
		if ( typeName != null && typeName.startsWith( AttributeConverterTypeAdapter.NAME_PREFIX ) ) {
			final String converterClassName = typeName.substring( AttributeConverterTypeAdapter.NAME_PREFIX.length() );
			final ClassLoaderService cls = getMetadata().getMetadataBuildingOptions()
					.getServiceRegistry()
					.getService( ClassLoaderService.class );
			try {
				final Class<AttributeConverter> converterClass = cls.classForName( converterClassName );
				attributeConverterDescriptor = new AttributeConverterDescriptorNonAutoApplicableImpl( converterClass.newInstance() );
				return;
			}
			catch (Exception e) {
				log.logBadHbmAttributeConverterType( typeName, e.getMessage() );
			}
		}

		this.typeName = typeName;
	}
