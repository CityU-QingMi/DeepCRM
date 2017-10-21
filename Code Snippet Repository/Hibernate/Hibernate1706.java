		@Override
		public <X> ValueExtractor<X> getExtractor(JavaTypeDescriptor<X> javaTypeDescriptor) {
			return new BasicExtractor<X>( javaTypeDescriptor, this ) {

				@Override
				protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
					Clob rsClob = rs.getClob( name );
					if ( rsClob == null || rsClob.length() < HANAClobTypeDescriptor.this.maxLobPrefetchSize ) {
						return javaTypeDescriptor.wrap( rsClob, options );
					}
					Clob clob = new MaterializedNClob( DataHelper.extractString( rsClob ) );
					return javaTypeDescriptor.wrap( clob, options );
				}

				@Override
				protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
					return javaTypeDescriptor.wrap( statement.getClob( index ), options );
				}

				@Override
				protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
					return javaTypeDescriptor.wrap( statement.getClob( name ), options );
				}
			};
		}
