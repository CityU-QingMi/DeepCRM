	@SuppressWarnings("")
	private void setLocalAttributeConverterDefinitions(List<Element> converterElements) {
		for ( Element converterElement : converterElements ) {
			final String className = converterElement.attributeValue( "class" );
			final String autoApplyAttribute = converterElement.attributeValue( "auto-apply" );
			final boolean autoApply = autoApplyAttribute != null && Boolean.parseBoolean( autoApplyAttribute );

			try {
				final Class<? extends AttributeConverter> attributeConverterClass = classLoaderAccess.classForName(
						className
				);
				attributeConverterDefinitions.add(
						new AttributeConverterDefinition( attributeConverterClass.newInstance(), autoApply )
				);
			}
			catch (ClassLoadingException e) {
				throw new AnnotationException( "Unable to locate specified AttributeConverter implementation class : " + className, e );
			}
			catch (Exception e) {
				throw new AnnotationException( "Unable to instantiate specified AttributeConverter implementation class : " + className, e );
			}
		}
	}
