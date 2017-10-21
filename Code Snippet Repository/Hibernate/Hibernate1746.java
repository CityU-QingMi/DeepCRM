	public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
		if ( sqlTypeDescriptor == null ) {
			throw new IllegalArgumentException( "sqlTypeDescriptor is null" );
		}
		if ( ! sqlTypeDescriptor.canBeRemapped() ) {
			return sqlTypeDescriptor;
		}

		final SqlTypeDescriptor overridden = getSqlTypeDescriptorOverride( sqlTypeDescriptor.getSqlType() );
		return overridden == null ? sqlTypeDescriptor : overridden;
	}
