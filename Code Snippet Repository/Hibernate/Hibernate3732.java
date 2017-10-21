	public CollectionFetchableIndexEntityGraph(
			CollectionReference collectionReference,
			ExpandingEntityQuerySpace entityQuerySpace) {
		super(
				entityQuerySpace,
				collectionReference.getPropertyPath().append( "<index>" )
		);

		this.collectionReference = collectionReference;
	}
