		@Override
		public <X> ValueExtractor<X> getExtractor(JavaTypeDescriptor<X> javaTypeDescriptor) {
			return new BasicExtractor<X>( javaTypeDescriptor, this ) {

				@Override
				protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
					Blob rsBlob = rs.getBlob( name );
					if ( rsBlob == null || rsBlob.length() < HANAStreamBlobTypeDescriptor.this.maxLobPrefetchSize ) {
						return javaTypeDescriptor.wrap( rsBlob, options );
					}
					Blob blob = new MaterializedBlob( DataHelper.extractBytes( rsBlob.getBinaryStream() ) );
					return javaTypeDescriptor.wrap( blob, options );
				}

				@Override
				protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
					return javaTypeDescriptor.wrap( statement.getBlob( index ), options );
				}

				@Override
				protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
					return javaTypeDescriptor.wrap( statement.getBlob( name ), options );
				}
			};
		}
