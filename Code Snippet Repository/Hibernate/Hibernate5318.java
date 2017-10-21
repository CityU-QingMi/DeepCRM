	@Override
	public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicExtractor<X>( javaTypeDescriptor, this ) {
			@Override
			protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
				return options.getJdbcTimeZone() != null ?
					javaTypeDescriptor.wrap( rs.getTimestamp( name, Calendar.getInstance( options.getJdbcTimeZone() ) ), options ) :
					javaTypeDescriptor.wrap( rs.getTimestamp( name ), options );
			}

			@Override
			protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
				return options.getJdbcTimeZone() != null ?
						javaTypeDescriptor.wrap( statement.getTimestamp( index, Calendar.getInstance( options.getJdbcTimeZone() ) ), options ) :
						javaTypeDescriptor.wrap( statement.getTimestamp( index ), options );
			}

			@Override
			protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
				return options.getJdbcTimeZone() != null ?
						javaTypeDescriptor.wrap( statement.getTimestamp( name, Calendar.getInstance( options.getJdbcTimeZone() ) ), options ) :
						javaTypeDescriptor.wrap( statement.getTimestamp( name ), options );
			}
		};
	}
