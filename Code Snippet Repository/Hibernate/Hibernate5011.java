	@Override
	public void nullSafeSet(PreparedStatement st, Object value,	int index, boolean[] settable, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		Serializable id;
		String entityName;
		if ( value == null ) {
			id = null;
			entityName = null;
		}
		else {
			entityName = session.bestGuessEntityName( value );
			id = ForeignKeys.getEntityIdentifierIfNotUnsaved( entityName, value, session );
		}

		// discriminatorType is assumed to be single-column type
		if ( settable == null || settable[0] ) {
			discriminatorType.nullSafeSet( st, entityName, index, session );
		}
		if ( settable == null ) {
			identifierType.nullSafeSet( st, id, index+1, session );
		}
		else {
			final boolean[] idSettable = new boolean[ settable.length-1 ];
			System.arraycopy( settable, 1, idSettable, 0, idSettable.length );
			identifierType.nullSafeSet( st, id, index+1, idSettable, session );
		}
	}
