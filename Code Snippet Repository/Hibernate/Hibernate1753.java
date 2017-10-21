		@Override
		public Blob mergeBlob(Blob original, Blob target, SharedSessionContractImplementor session) {
			if ( original == null && target == null ) {
				return null;
			}
			try {
				final LobCreator lobCreator = session.getFactory().getServiceRegistry().getService( JdbcServices.class ).getLobCreator(
						session
				);
				return original == null
						? lobCreator.createBlob( ArrayHelper.EMPTY_BYTE_ARRAY )
						: lobCreator.createBlob( original.getBinaryStream(), original.length() );
			}
			catch (SQLException e) {
				throw session.getFactory().getSQLExceptionHelper().convert( e, "unable to merge BLOB data" );
			}
		}
