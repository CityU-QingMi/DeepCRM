	public void addWorkUnit(AuditWorkUnit vwu) {
		if ( vwu.containsWork() ) {
			final Object entityId = vwu.getEntityId();

			if ( entityId == null ) {
				// Just adding the work unit - it's not associated with any persistent entity.
				workUnits.offer( vwu );
			}
			else {
				final String entityName = vwu.getEntityName();
				final Pair<String, Object> usedIdsKey = Pair.make( entityName, entityId );

				if ( usedIds.containsKey( usedIdsKey ) ) {
					final AuditWorkUnit other = usedIds.get( usedIdsKey );
					final AuditWorkUnit result = vwu.dispatch( other );

					if ( result != other ) {
						removeWorkUnit( other );

						if ( result != null ) {
							usedIds.put( usedIdsKey, result );
							workUnits.offer( result );
						}
						// else: a null result means that no work unit should be kept
					}
					// else: the result is the same as the work unit already added. No need to do anything.
				}
				else {
					usedIds.put( usedIdsKey, vwu );
					workUnits.offer( vwu );
				}
			}
		}
	}
