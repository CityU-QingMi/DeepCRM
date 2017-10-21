	public JoinSequence createCollectionJoinSequence(QueryableCollection collPersister, String collectionName) {
		JoinSequence joinSequence = createJoinSequence();
		joinSequence.setRoot( collPersister, collectionName );
		joinSequence.setUseThetaStyle( true );        // TODO: figure out how this should be set.
///////////////////////////////////////////////////////////////////////////////
// This was the reason for failures regarding INDEX_OP and subclass joins on
// theta-join dialects; not sure what behavior we were trying to emulate ;)
//		joinSequence = joinSequence.getFromPart();	// Emulate the old addFromOnly behavior.
		return joinSequence;
	}
