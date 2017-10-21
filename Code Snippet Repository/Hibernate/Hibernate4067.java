	@Override
	public Object getElementByIndex(Serializable key, Object index, SharedSessionContractImplementor session, Object owner) {
		try {
			PreparedStatement st = session
					.getJdbcCoordinator()
					.getStatementPreparer()
					.prepareStatement( sqlSelectRowByIndexString );
			try {
				getKeyType().nullSafeSet( st, key, 1, session );
				getIndexType().nullSafeSet( st, incrementIndexByBase( index ), keyColumnNames.length + 1, session );
				ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st );
				try {
					if ( rs.next() ) {
						return getElementType().nullSafeGet( rs, elementColumnAliases, session, owner );
					}
					else {
						return null;
					}
				}
				finally {
					session.getJdbcCoordinator().getResourceRegistry().release( rs, st );
				}
			}
			finally {
				session.getJdbcCoordinator().getResourceRegistry().release( st );
				session.getJdbcCoordinator().afterStatementExecution();
			}
		}
		catch ( SQLException sqle ) {
			throw getSQLExceptionHelper().convert(
					sqle,
					"could not read row: " +
							MessageHelper.collectionInfoString( this, key, getFactory() ),
					sqlSelectSizeString
			);
		}
	}
