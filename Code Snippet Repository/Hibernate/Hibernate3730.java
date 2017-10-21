	public CollectionFetchableElementEntityGraph(
			CollectionReference collectionReference,
			ExpandingEntityQuerySpace entityQuerySpace) {
		super(
				entityQuerySpace,
				collectionReference.getPropertyPath().append( "<elements>" )
		);

		this.collectionReference = collectionReference;
	}
