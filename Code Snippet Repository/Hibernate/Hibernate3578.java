	@Override
	protected Object[] getResultRow(Object[] row, ResultSet rs, SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		final Object[] result;
		if ( translator.hasProjection() ) {
			Type[] types = translator.getProjectedTypes();
			result = new Object[types.length];
			String[] columnAliases = translator.getProjectedColumnAliases();
			for ( int i = 0, pos = 0; i < result.length; i++ ) {
				int numColumns = types[i].getColumnSpan( session.getFactory() );
				if ( numColumns > 1 ) {
					String[] typeColumnAliases = ArrayHelper.slice( columnAliases, pos, numColumns );
					result[i] = types[i].nullSafeGet( rs, typeColumnAliases, session, null );
				}
				else {
					result[i] = types[i].nullSafeGet( rs, columnAliases[pos], session, null );
				}
				pos += numColumns;
			}
		}
		else {
			result = toResultRow( row );
		}
		return result;
	}
