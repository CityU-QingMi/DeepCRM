	public PersistentSet(SharedSessionContractImplementor session, java.util.Set set) {
		super( session );
		// Sets can be just a view of a part of another collection.
		// do we need to copy it to be sure it won't be changing
		// underneath us?
		// ie. this.set.addAll(set);
		this.set = set;
		setInitialized();
		setDirectlyAccessible( true );
	}
