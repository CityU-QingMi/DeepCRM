	@Override
	public <X> ValueExtractor<X> getExtractor(JavaTypeDescriptor<X> javaTypeDescriptor) {
		// Get the extractor for the intermediate type representation
		final ValueExtractor realExtractor = delegate.getExtractor( intermediateJavaTypeDescriptor );

		return new ValueExtractor<X>() {
			@Override
			public X extract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
				return doConversion( realExtractor.extract( rs, name, options ) );
			}

			@Override
			public X extract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
				return doConversion( realExtractor.extract( statement, index, options ) );
			}

			@Override
			public X extract(CallableStatement statement, String[] paramNames, WrapperOptions options) throws SQLException {
				if ( paramNames.length > 1 ) {
					throw new IllegalArgumentException( "Basic value extraction cannot handle multiple output parameters" );
				}
				return doConversion( realExtractor.extract( statement, paramNames, options ) );
			}

			@SuppressWarnings("unchecked")
			private X doConversion(Object extractedValue) {
				try {
					X convertedValue = (X) converter.convertToEntityAttribute( extractedValue );
					log.debugf( "Converted value on extraction: %s -> %s", extractedValue, convertedValue );
					return convertedValue;
				}
				catch (PersistenceException pe) {
					throw pe;
				}
				catch (RuntimeException re) {
					throw new PersistenceException( "Error attempting to apply AttributeConverter", re );
				}
			}
		};
	}
