		@Override
		public <X> BasicBinder<X> getNClobBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
			return new BasicBinder<X>( javaTypeDescriptor, this ) {
				@Override
				protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options)
						throws SQLException {
					if ( options.useStreamForLobBinding() ) {
						STREAM_BINDING.getNClobBinder( javaTypeDescriptor ).doBind( st, value, index, options );
					}
					else {
						NCLOB_BINDING.getNClobBinder( javaTypeDescriptor ).doBind( st, value, index, options );
					}
				}

				@Override
				protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
						throws SQLException {
					if ( options.useStreamForLobBinding() ) {
						STREAM_BINDING.getNClobBinder( javaTypeDescriptor ).doBind( st, value, name, options );
					}
					else {
						NCLOB_BINDING.getNClobBinder( javaTypeDescriptor ).doBind( st, value, name, options );
					}
				}
			};
		}
