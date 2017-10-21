	private void writeObject(ObjectOutputStream oos) throws IOException {
		log.tracef( "Serializing Session [%s]", getSessionIdentifier() );

		oos.defaultWriteObject();

		persistenceContext.serialize( oos );
		actionQueue.serialize( oos );

		oos.writeObject( loadQueryInfluencers );
	}
