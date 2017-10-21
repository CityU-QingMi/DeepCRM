	public static NativeSQLQueryReturn extractReturnDescription(
			JaxbHbmNativeQueryCollectionLoadReturnType rtnSource,
			HbmLocalMetadataBuildingContext context,
			int queryReturnPosition) {
		final int dot = rtnSource.getRole().lastIndexOf( '.' );
		if ( dot == -1 ) {
			throw new MappingException(
					String.format(
							Locale.ENGLISH,
							"Collection attribute for sql query return [%s] not formatted correctly {OwnerClassName.propertyName}",
							rtnSource.getAlias()
					),
					context.getOrigin()
			);
		}

		String ownerClassName = context.findEntityBinding( null, rtnSource.getRole().substring( 0, dot ) )
				.getClassName();
		String ownerPropertyName = rtnSource.getRole().substring( dot + 1 );

		return new NativeSQLQueryCollectionReturn(
				rtnSource.getAlias(),
				ownerClassName,
				ownerPropertyName,
				// FIXME: get the PersistentClass
				extractPropertyResults( rtnSource.getAlias(), rtnSource, null, context ),
				rtnSource.getLockMode()
		);
	}
