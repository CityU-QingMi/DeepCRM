	private void broadcastInvalidateForPrepare(List<WriteCommand> modifications, InvocationContext ctx) throws Throwable {
		// A prepare does not carry flags, so skip checking whether is local or not
		if ( ctx.isInTxScope() ) {
			if ( modifications.isEmpty() ) {
				return;
			}

			InvalidationFilterVisitor filterVisitor = new InvalidationFilterVisitor( modifications.size() );
			filterVisitor.visitCollection( null, modifications );

			if ( filterVisitor.containsPutForExternalRead ) {
				log.debug( "Modification list contains a putForExternalRead operation.  Not invalidating." );
			}
			else if ( filterVisitor.containsLocalModeFlag ) {
				log.debug( "Modification list contains a local mode flagged operation.  Not invalidating." );
			}
			else {
				try {
					invalidateAcrossCluster( defaultSynchronous, filterVisitor.result.toArray(), ctx );
				}
				catch (Throwable t) {
					log.unableToRollbackInvalidationsDuringPrepare( t );
					if ( t instanceof RuntimeException ) {
						throw t;
					}
					else {
						throw new RuntimeException( "Unable to broadcast invalidation messages", t );
					}
				}
			}
		}
	}
