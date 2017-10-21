	public Object getCurrentRevisionData(Session session, boolean persist) {
		// Generating the revision data if not yet generated
		if ( revisionData == null ) {
			revisionData = revisionInfoGenerator.generate();
		}

		// Saving the revision data, if not yet saved and persist is true
		if ( !session.contains( revisionData ) && persist ) {
			revisionInfoGenerator.saveRevisionData( session, revisionData );
		}

		return revisionData;
	}
