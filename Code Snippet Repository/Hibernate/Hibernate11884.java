	@Override
	public void bind(
			CallableStatement st, J value, String name, WrapperOptions options) throws SQLException {
		if ( value == null ) {
			st.setNull( name, Types.STRUCT, SQL_TYPE_NAME );
		}
		else {
			final Geometry geometry = javaTypeDescriptor.unwrap( value, Geometry.class, options );
			final Object dbGeom = toNative( geometry, st.getConnection() );
			st.setObject( name, dbGeom );
		}
	}
