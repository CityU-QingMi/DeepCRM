	@Override
	public void topLevelMergeComplete(EventSource session) {
		// Log the summary.
		if ( countsByEntityName != null ) {
			for ( Map.Entry<String, Integer> entry : countsByEntityName.entrySet() ) {
				final String entityName = entry.getKey();
				final int count = entry.getValue();
				LOG.debug(
						String.format(
								"Summary: number of %s entities with multiple representations merged: %d",
								entityName,
								count
						)
				);
			}
		}
		else {
			LOG.debug( "No entity copies merged." );
		}

		if ( managedToMergeEntitiesXref != null ) {
			for ( Map.Entry<Object,Set<Object>> entry : managedToMergeEntitiesXref.entrySet() ) {
				Object managedEntity = entry.getKey();
				Set mergeEntities = entry.getValue();
				StringBuilder sb = new StringBuilder( "Details: merged ")
						.append( mergeEntities.size() )
						.append( " representations of the same entity " )
						.append(
								MessageHelper.infoString(
										session.getEntityName( managedEntity ),
										session.getIdentifier( managedEntity )
								)
						)
						.append( " being merged: " );
				boolean first = true;
				for ( Object mergeEntity : mergeEntities ) {
					if ( first ) {
						first = false;
					}
					else {
						sb.append( ", " );
					}
					sb.append(  getManagedOrDetachedEntityString( managedEntity, mergeEntity ) );
				}
				sb.append( "; resulting managed entity: [" ).append( managedEntity ).append( ']' );
				LOG.debug( sb.toString());
			}
		}
	}
