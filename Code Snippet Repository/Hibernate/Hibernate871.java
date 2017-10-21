	public static FetchCharacteristicsSingularAssociationImpl interpretManyToManyElement(
			MappingDefaults mappingDefaults,
			JaxbHbmFetchStyleEnum fetchMapping,
			JaxbHbmOuterJoinEnum outerJoinMapping,
			JaxbHbmLazyEnum lazyMapping) {
		final Builder builder = new Builder( mappingDefaults );

		// #initOuterJoinFetchSetting
		if ( fetchMapping == null ) {
			if ( outerJoinMapping == null ) {
				builder.setFetchTiming( FetchTiming.IMMEDIATE );
				builder.setFetchStyle( FetchStyle.JOIN );
			}
			else {
				//NOTE <many-to-many outer-join="..." is deprecated.:
				// Default to join and non-lazy for the "second join"
				// of the many-to-many
				DeprecationLogger.DEPRECATION_LOGGER.deprecatedManyToManyOuterJoin();
				builder.setFetchTiming( FetchTiming.IMMEDIATE );
				builder.setFetchStyle( FetchStyle.JOIN );
			}
		}
		else {
			//NOTE <many-to-many fetch="..." is deprecated.:
			// Default to join and non-lazy for the "second join"
			// of the many-to-many
			DeprecationLogger.DEPRECATION_LOGGER.deprecatedManyToManyFetch();
			builder.setFetchTiming( FetchTiming.IMMEDIATE );
			builder.setFetchStyle( FetchStyle.JOIN );
		}

		// #initLaziness
		if ( lazyMapping != null ) {
			if ( lazyMapping == JaxbHbmLazyEnum.FALSE ) {
				builder.setFetchTiming( FetchTiming.IMMEDIATE );
			}
		}

		return builder.createFetchCharacteristics();
	}
