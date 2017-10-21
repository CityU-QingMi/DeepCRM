	public CollectionFetchableElementCompositeGraph(
			CollectionReference collectionReference,
			ExpandingCompositeQuerySpace compositeQuerySpace) {
		super(
				compositeQuerySpace,
				false,
				// these property paths are just informational...
				collectionReference.getPropertyPath().append( "<element>" )
		);
		this.collectionReference = collectionReference;
	}
