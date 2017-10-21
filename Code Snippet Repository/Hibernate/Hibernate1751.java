		@Override
		public Clob mergeClob(Clob original, Clob target, SharedSessionContractImplementor session) {
			if ( original != target ) {
				try {
					// the CLOB just read during the load phase of merge
					final OutputStream connectedStream = target.setAsciiStream( 1L );
					// the CLOB from the detached state
					final InputStream detachedStream = original.getAsciiStream();
					StreamCopier.copy( detachedStream, connectedStream );
					return target;
				}
				catch (SQLException e ) {
					throw session.getFactory().getSQLExceptionHelper().convert( e, "unable to merge CLOB data" );
				}
			}
			else {
				return NEW_LOCATOR_LOB_MERGE_STRATEGY.mergeClob( original, target, session );
			}
		}
