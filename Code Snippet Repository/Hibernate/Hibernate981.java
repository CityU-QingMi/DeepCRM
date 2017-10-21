	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		JdbcDataType jdbcDataType = (JdbcDataType) o;

		return typeCode == jdbcDataType.typeCode
				&& javaType.equals( jdbcDataType.javaType )
				&& typeName.equals( jdbcDataType.typeName );

	}
