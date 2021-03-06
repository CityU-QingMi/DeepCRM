	@Override
	public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicBinder<X>( javaTypeDescriptor, this ) {
			@Override
			protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options)
					throws SQLException {
				final Geometry geometry = getJavaDescriptor().unwrap( value, Geometry.class, options );
				final byte[] bytes = Encoders.encode( geometry );
				st.setObject( index, bytes );
			}

			@Override
			protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
					throws SQLException {
				final Geometry geometry = getJavaDescriptor().unwrap( value, Geometry.class, options );
				final byte[] bytes = Encoders.encode( geometry );
				st.setObject( name, bytes );
			}

		};
	}
