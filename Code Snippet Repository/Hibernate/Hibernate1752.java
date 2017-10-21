		@Override
		public NClob mergeNClob(NClob original, NClob target, SharedSessionContractImplementor session) {
			if ( original != target ) {
				try {
					// the NCLOB just read during the load phase of merge
					final OutputStream connectedStream = target.setAsciiStream( 1L );
					// the NCLOB from the detached state
					final InputStream detachedStream = original.getAsciiStream();
					StreamCopier.copy( detachedStream, connectedStream );
					return target;
				}
				catch (SQLException e ) {
					throw session.getFactory().getSQLExceptionHelper().convert( e, "unable to merge NCLOB data" );
				}
			}
			else {
				return NEW_LOCATOR_LOB_MERGE_STRATEGY.mergeNClob( original, target, session );
			}
		}
