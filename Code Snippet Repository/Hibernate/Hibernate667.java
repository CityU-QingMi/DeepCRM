	@Override
	@SuppressWarnings({ "" })
	public void addUniqueConstraints(Table table, List uniqueConstraints) {
		List<UniqueConstraintHolder> constraintHolders = new ArrayList<UniqueConstraintHolder>(
				CollectionHelper.determineProperSizing( uniqueConstraints.size() )
		);

		int keyNameBase = determineCurrentNumberOfUniqueConstraintHolders( table );
		for ( String[] columns : ( List<String[]> ) uniqueConstraints ) {
			final String keyName = "key" + keyNameBase++;
			constraintHolders.add(
					new UniqueConstraintHolder().setName( keyName ).setColumns( columns )
			);
		}
		addUniqueConstraintHolders( table, constraintHolders );
	}
