	@Override
	public Map<String, Object> generateData(Object revisionData) {
		// Generating data with the nested work unit. This data contains all data except the fake relation.
		// Making a defensive copy not to modify the data held by the nested work unit.
		final Map<String, Object> nestedData = new HashMap<>( nestedWorkUnit.generateData( revisionData ) );

		// Now adding data for all fake relations.
		for ( FakeRelationChange fakeRelationChange : fakeRelationChanges.values() ) {
			fakeRelationChange.generateData( sessionImplementor, nestedData );
		}

		return nestedData;
	}
