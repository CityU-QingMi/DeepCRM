	@Override
	public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicBinder<X>( javaTypeDescriptor, this ) {
			@Override
			protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
				final Timestamp timestamp = javaTypeDescriptor.unwrap( value, Timestamp.class, options );
				if ( value instanceof Calendar ) {
					st.setTimestamp( index, timestamp, (Calendar) value );
				}
				else if (options.getJdbcTimeZone() != null) {
					st.setTimestamp( index, timestamp, Calendar.getInstance( options.getJdbcTimeZone() ) );
				}
				else {
					st.setTimestamp( index, timestamp );
				}
			}

			@Override
			protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
					throws SQLException {
				final Timestamp timestamp = javaTypeDescriptor.unwrap( value, Timestamp.class, options );
				if ( value instanceof Calendar ) {
					st.setTimestamp( name, timestamp, (Calendar) value );
				}
				else if (options.getJdbcTimeZone() != null) {
					st.setTimestamp( name, timestamp, Calendar.getInstance( options.getJdbcTimeZone() ) );
				}
				else {
					st.setTimestamp( name, timestamp );
				}
			}
		};
	}
