	public JdbcDataType(int typeCode, String typeName, Class javaType) {
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.javaType = javaType;

		int result = typeCode;
		if ( typeName != null ) {
			result = 31 * result + typeName.hashCode();
		}
		if ( javaType != null ) {
			result = 31 * result + javaType.hashCode();
		}
		this.hashCode = result;
	}
