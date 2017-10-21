	@Override
	public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicExtractor<X>( javaTypeDescriptor, this ) {
			@Override
			protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
				return options.getJdbcTimeZone() != null ?
						javaTypeDescriptor.wrap( rs.getTime( name, Calendar.getInstance( options.getJdbcTimeZone() ) ), options ) :
						javaTypeDescriptor.wrap( rs.getTime( name ), options );
			}

			@Override
			protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
				return options.getJdbcTimeZone() != null ?
						javaTypeDescriptor.wrap( statement.getTime( index, Calendar.getInstance( options.getJdbcTimeZone() ) ), options ) :
						javaTypeDescriptor.wrap( statement.getTime( index ), options );
			}

			@Override
			protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
				return options.getJdbcTimeZone() != null ?
						javaTypeDescriptor.wrap( statement.getTime( name, Calendar.getInstance( options.getJdbcTimeZone() ) ), options ) :
						javaTypeDescriptor.wrap( statement.getTime( name ), options );
			}
		};
	}
