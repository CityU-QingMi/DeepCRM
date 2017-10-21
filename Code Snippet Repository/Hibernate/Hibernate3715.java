	protected AbstractCollectionReference(
			ExpandingCollectionQuerySpace collectionQuerySpace,
			PropertyPath propertyPath,
			boolean shouldIncludeJoins) {
		this.collectionQuerySpace = collectionQuerySpace;
		this.propertyPath = propertyPath;

		this.allowElementJoin = shouldIncludeJoins;

		// Currently we can only allow a join for the collection index if all of the following are true:
		// - collection element joins are allowed;
		// - index is an EntityType;
		// - index values are not "formulas" (e.g., a @MapKey index is translated into "formula" value(s)).
		// Hibernate cannot currently support eager joining of associations within a component (@Embeddable) as an index.
		if ( shouldIncludeJoins &&
				collectionQuerySpace.getCollectionPersister().hasIndex() &&
				collectionQuerySpace.getCollectionPersister().getIndexType().isEntityType()  ) {
			final String[] indexFormulas =
					( (QueryableCollection) collectionQuerySpace.getCollectionPersister() ).getIndexFormulas();
			final int nNonNullFormulas = ArrayHelper.countNonNull( indexFormulas );
			this.allowIndexJoin = nNonNullFormulas == 0;
		}
		else {
			this.allowIndexJoin = false;
		}

		// All other fields must be initialized before building this.index and this.element.
		this.index = buildIndexGraph();
		this.element = buildElementGraph();
	}
