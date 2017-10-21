	private static Map<String, String[]> extractPropertyResults(
			String alias,
			JaxbHbmNativeQueryReturnType rtnSource,
			PersistentClass pc,
			HbmLocalMetadataBuildingContext context) {
		Map<String, String[]> results = extractPropertyResults(
				alias,
				(NativeQueryNonScalarRootReturn) rtnSource,
				pc,
				context
		);

		if ( rtnSource.getReturnDiscriminator() != null ) {
			if ( results == null ) {
				results = new HashMap<String, String[]>();
			}

			final String column = rtnSource.getReturnDiscriminator().getColumn();
			if ( column == null ) {
				throw new MappingException(
						String.format(
								Locale.ENGLISH,
								"return-discriminator [%s (%s)] did not specify column",
								pc.getEntityName(),
								alias
						),
						context.getOrigin()
				);
			}

			results.put( "class", new String[] {column} );
		}

		return results;
	}
