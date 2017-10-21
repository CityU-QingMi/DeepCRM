		@Override
		public <X> ValueExtractor<X> getExtractor(JavaTypeDescriptor<X> javaTypeDescriptor) {
			return new BasicExtractor<X>( javaTypeDescriptor, this ) {

				@Override
				protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
					NClob rsNClob = rs.getNClob( name );
					if ( rsNClob == null || rsNClob.length() < HANANClobTypeDescriptor.this.maxLobPrefetchSize ) {
						return javaTypeDescriptor.wrap( rsNClob, options );
					}
					NClob nClob = new MaterializedNClob( DataHelper.extractString( rsNClob ) );
					return javaTypeDescriptor.wrap( nClob, options );
				}

				@Override
				protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
					return javaTypeDescriptor.wrap( statement.getNClob( index ), options );
				}

				@Override
				protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
					return javaTypeDescriptor.wrap( statement.getNClob( name ), options );
				}
			};
		}
