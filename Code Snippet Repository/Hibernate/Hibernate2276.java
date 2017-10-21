	private void addResolvedEntityInsertAction(AbstractEntityInsertAction insert) {
		if ( insert.isEarlyInsert() ) {
			LOG.trace( "Executing insertions before resolved early-insert" );
			executeInserts();
			LOG.debug( "Executing identity-insert immediately" );
			execute( insert );
		}
		else {
			LOG.trace( "Adding resolved non-early insert action." );
			addAction( AbstractEntityInsertAction.class, insert );
		}
		insert.makeEntityManaged();
		if( unresolvedInsertions != null ) {
			for ( AbstractEntityInsertAction resolvedAction : unresolvedInsertions.resolveDependentActions( insert.getInstance(), session ) ) {
				addResolvedEntityInsertAction( resolvedAction );
			}
		}
	}
