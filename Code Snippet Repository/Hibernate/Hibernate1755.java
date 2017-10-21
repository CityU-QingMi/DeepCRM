		@Override
		public NClob mergeNClob(NClob original, NClob target, SharedSessionContractImplementor session) {
			if ( original == null && target == null ) {
				return null;
			}
			try {
				final LobCreator lobCreator = session.getFactory().getServiceRegistry().getService( JdbcServices.class ).getLobCreator( session );
				return original == null
						? lobCreator.createNClob( "" )
						: lobCreator.createNClob( original.getCharacterStream(), original.length() );
			}
			catch (SQLException e) {
				throw session.getFactory().getSQLExceptionHelper().convert( e, "unable to merge NCLOB data" );
			}
		}
