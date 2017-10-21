	@Override
	public void performDiscovery(JdbcResultMetadata metadata, List<Type> types, List<String> aliases) throws SQLException {
		if ( alias == null ) {
			alias = metadata.getColumnName( position );
		}
		else if ( position < 0 ) {
			position = metadata.resolveColumnPosition( alias );
		}
		if ( type == null ) {
			type = metadata.getHibernateType( position );
		}
		types.add( type );
		aliases.add( alias );
	}
