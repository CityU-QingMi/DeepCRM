	@Override
	public AuditWorkUnit merge(FakeBidirectionalRelationWorkUnit second) {
		// First merging the nested work units.
		final AuditWorkUnit mergedNested = second.getNestedWorkUnit().dispatch( nestedWorkUnit );

		// Now merging the fake relation changes from both work units.
		final Map<String, FakeRelationChange> secondFakeRelationChanges = second.getFakeRelationChanges();
		final Map<String, FakeRelationChange> mergedFakeRelationChanges = new HashMap<>();
		final Set<String> allPropertyNames = new HashSet<>( fakeRelationChanges.keySet() );
		allPropertyNames.addAll( secondFakeRelationChanges.keySet() );

		for ( String propertyName : allPropertyNames ) {
			mergedFakeRelationChanges.put(
					propertyName,
					FakeRelationChange.merge(
							fakeRelationChanges.get( propertyName ),
							secondFakeRelationChanges.get( propertyName )
					)
			);
		}

		return new FakeBidirectionalRelationWorkUnit( this, mergedFakeRelationChanges, mergedNested );
	}
