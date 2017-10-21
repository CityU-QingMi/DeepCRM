		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			final String methodName = method.getName();
			if ( "setNCharacterStream".equals( methodName ) ) {
				if ( wrapperOptions.useStreamForLobBinding() ) {
					return null;
				}
				else {
					throw new IllegalStateException( "PreparedStatement#setNCharacterStream unexpectedly called" );
				}
			}
			else if ( "setNClob".equals( methodName ) ) {
				if ( !wrapperOptions.useStreamForLobBinding() ) {
					return null;
				}
				else {
					throw new IllegalStateException( "PreparedStatement#setNClob unexpectedly called" );
				}
			}
			else {
				throw new UnsupportedOperationException( methodName + " is not supported." );

			}
		}
