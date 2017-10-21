	@Override
	public void bind(PreparedStatement st, J value, int index, WrapperOptions options) throws SQLException {
		if ( value == null ) {
			st.setNull( index, Types.STRUCT, SQL_TYPE_NAME );
		}
		else {
			final Geometry geometry = javaTypeDescriptor.unwrap( value, Geometry.class, options );
			final Object dbGeom = toNative( geometry, st.getConnection() );
			st.setObject( index, dbGeom );
		}
	}
