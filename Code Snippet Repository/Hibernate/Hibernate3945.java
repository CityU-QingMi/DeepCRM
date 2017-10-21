	public Type getType() throws MappingException {
		if ( type != null ) {
			return type;
		}

		if ( typeName == null ) {
			throw new MappingException( "No type name" );
		}

		if ( typeParameters != null
				&& Boolean.valueOf( typeParameters.getProperty( DynamicParameterizedType.IS_DYNAMIC ) )
				&& typeParameters.get( DynamicParameterizedType.PARAMETER_TYPE ) == null ) {
			createParameterImpl();
		}

		Type result = metadata.getTypeResolver().heuristicType( typeName, typeParameters );
		// if this is a byte[] version/timestamp, then we need to use RowVersionType
		// instead of BinaryType (HHH-10413)
		if ( isVersion && BinaryType.class.isInstance( result ) ) {
			log.debug( "version is BinaryType; changing to RowVersionType" );
			result = RowVersionType.INSTANCE;
		}
		if ( result == null ) {
			String msg = "Could not determine type for: " + typeName;
			if ( table != null ) {
				msg += ", at table: " + table.getName();
			}
			if ( columns != null && columns.size() > 0 ) {
				msg += ", for columns: " + columns;
			}
			throw new MappingException( msg );
		}

		return result;
	}
