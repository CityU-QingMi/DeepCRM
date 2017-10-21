	@Override
	public AttributeConverterDescriptor findAutoApplyConverterForAttribute(
			XProperty xProperty,
			MetadataBuildingContext context) {
		List<AttributeConverterDescriptor> matched = new ArrayList<AttributeConverterDescriptor>();

		for ( AttributeConverterDescriptor descriptor : converterDescriptors() ) {
			log.debugf(
					"Checking auto-apply AttributeConverter [%s] (type=%s) for match against attribute : %s.%s (type=%s)",
					descriptor.toString(),
					descriptor.getDomainType().getSimpleName(),
					xProperty.getDeclaringClass().getName(),
					xProperty.getName(),
					xProperty.getType().getName()
			);

			if ( descriptor.shouldAutoApplyToAttribute( xProperty, context ) ) {
				matched.add( descriptor );
			}
		}

		if ( matched.isEmpty() ) {
			return null;
		}

		if ( matched.size() == 1 ) {
			return matched.get( 0 );
		}

		// otherwise, we had multiple matches
		throw new RuntimeException(
				String.format(
						Locale.ROOT,
						"Multiple auto-apply converters matched attribute [%s.%s] : %s",
						xProperty.getDeclaringClass().getName(),
						xProperty.getName(),
						StringHelper.join( matched, RENDERER )
				)
		);
	}
