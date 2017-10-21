		@Override
		public Clob mergeClob(Clob original, Clob target, SharedSessionContractImplementor session) {
			if ( original == null && target == null ) {
				return null;
			}
			try {
				final LobCreator lobCreator = session.getFactory().getServiceRegistry().getService( JdbcServices.class ).getLobCreator( session );
				return original == null
						? lobCreator.createClob( "" )
						: lobCreator.createClob( original.getCharacterStream(), original.length() );
			}
			catch (SQLException e) {
				throw session.getFactory().getSQLExceptionHelper().convert( e, "unable to merge CLOB data" );
			}
		}
