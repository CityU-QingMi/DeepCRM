	@Override
	public void serialize(ObjectOutputStream oos) throws IOException {
		final Status previousStatus = getPreviousStatus();
		oos.writeObject( getEntityName() );
		oos.writeObject( id );
		oos.writeObject( getStatus().name() );
		oos.writeObject( (previousStatus == null ? "" : previousStatus.name()) );
		// todo : potentially look at optimizing these two arrays
		oos.writeObject( loadedState );
		oos.writeObject( getDeletedState() );
		oos.writeObject( version );
		oos.writeObject( getLockMode().toString() );
		oos.writeBoolean( isExistsInDatabase() );
		oos.writeBoolean( isBeingReplicated() );
	}
