		@Override
		public <X> BasicBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
			return new BasicBinder<X>( javaTypeDescriptor, this ) {

				@Override
				protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
					SqlTypeDescriptor descriptor = BlobTypeDescriptor.BLOB_BINDING;
					if ( byte[].class.isInstance( value ) ) {
						// performance shortcut for binding BLOB data in byte[] format
						descriptor = BlobTypeDescriptor.PRIMITIVE_ARRAY_BINDING;
					}
					else if ( options.useStreamForLobBinding() ) {
						descriptor = HANABlobTypeDescriptor.this.hanaStreamBlobTypeDescriptor;
					}
					descriptor.getBinder( javaTypeDescriptor ).bind( st, value, index, options );
				}

				@Override
				protected void doBind(CallableStatement st, X value, String name, WrapperOptions options) throws SQLException {
					SqlTypeDescriptor descriptor = BlobTypeDescriptor.BLOB_BINDING;
					if ( byte[].class.isInstance( value ) ) {
						// performance shortcut for binding BLOB data in byte[] format
						descriptor = BlobTypeDescriptor.PRIMITIVE_ARRAY_BINDING;
					}
					else if ( options.useStreamForLobBinding() ) {
						descriptor = HANABlobTypeDescriptor.this.hanaStreamBlobTypeDescriptor;
					}
					descriptor.getBinder( javaTypeDescriptor ).bind( st, value, name, options );
				}
			};
		}
