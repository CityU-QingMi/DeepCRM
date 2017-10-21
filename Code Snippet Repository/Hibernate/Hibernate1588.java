	@SuppressWarnings("")
	public PersistentIdentifierBag(SharedSessionContractImplementor session, Collection coll) {
		super( session );
		if (coll instanceof List) {
			values = (List<Object>) coll;
		}
		else {
			values = new ArrayList<>();
			for ( Object element : coll ) {
				values.add( element );
			}
		}
		setInitialized();
		setDirectlyAccessible( true );
		identifiers = new HashMap<>();
	}
