	@SuppressWarnings("")
	public PersistentBag(SharedSessionContractImplementor session, Collection coll) {
		super( session );
		if ( coll instanceof List ) {
			bag = (List) coll;
		}
		else {
			bag = new ArrayList();
			for ( Object element : coll ) {
				bag.add( element );
			}
		}
		setInitialized();
		setDirectlyAccessible( true );
	}
