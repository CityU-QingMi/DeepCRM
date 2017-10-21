	private void registerQuerySpace(QuerySpace querySpace) {
		log.debugf(
				"Adding QuerySpace : uid = %s -> %s]",
				querySpace.getUid(),
				querySpace
		);
		final QuerySpace previous = querySpaceByUid.put( querySpace.getUid(), querySpace );
		if ( previous != null ) {
			throw new IllegalStateException( "Encountered duplicate QuerySpace uid : " + querySpace.getUid() );
		}
	}
