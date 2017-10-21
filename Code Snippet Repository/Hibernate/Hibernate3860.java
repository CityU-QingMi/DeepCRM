	public int getSqlTypeCode(Mapping mapping) throws MappingException {
		org.hibernate.type.Type type = getValue().getType();
		try {
			int sqlTypeCode = type.sqlTypes( mapping )[getTypeIndex()];
			if ( getSqlTypeCode() != null && getSqlTypeCode() != sqlTypeCode ) {
				throw new MappingException( "SQLType code's does not match. mapped as " + sqlTypeCode + " but is " + getSqlTypeCode() );
			}
			return sqlTypeCode;
		}
		catch (Exception e) {
			throw new MappingException(
					"Could not determine type for column " +
							name +
							" of type " +
							type.getClass().getName() +
							": " +
							e.getClass().getName(),
					e
			);
		}
	}
