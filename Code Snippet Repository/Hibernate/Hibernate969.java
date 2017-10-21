	public static NativeSQLQueryJoinReturn extractReturnDescription(
			JaxbHbmNativeQueryJoinReturnType rtnSource,
			HbmLocalMetadataBuildingContext context,
			int queryReturnPosition) {
		final int dot = rtnSource.getProperty().lastIndexOf( '.' );
		if ( dot == -1 ) {
			throw new MappingException(
					String.format(
							Locale.ENGLISH,
							"Role attribute for sql query return [%s] not formatted correctly {owningAlias.propertyName}",
							rtnSource.getAlias()
					),
					context.getOrigin()
			);
		}

		String roleOwnerAlias = rtnSource.getProperty().substring( 0, dot );
		String roleProperty = rtnSource.getProperty().substring( dot + 1 );

		return new NativeSQLQueryJoinReturn(
				rtnSource.getAlias(),
				roleOwnerAlias,
				roleProperty,
				//FIXME: get the PersistentClass
				extractPropertyResults( rtnSource.getAlias(), rtnSource, null, context ),
				rtnSource.getLockMode()
		);
	}
