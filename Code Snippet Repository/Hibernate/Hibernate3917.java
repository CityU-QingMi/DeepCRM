	protected void checkColumnDuplication() {
		HashSet cols = new HashSet();
		if ( getIdentifierMapper() == null ) {
			//an identifier mapper => getKey will be included in the getNonDuplicatedPropertyIterator()
			//and checked later, so it needs to be excluded
			checkColumnDuplication( cols, getKey().getColumnIterator() );
		}
		checkColumnDuplication( cols, getDiscriminatorColumnIterator() );
		checkPropertyColumnDuplication( cols, getNonDuplicatedPropertyIterator() );
		Iterator iter = getJoinIterator();
		while ( iter.hasNext() ) {
			cols.clear();
			Join join = (Join) iter.next();
			checkColumnDuplication( cols, join.getKey().getColumnIterator() );
			checkPropertyColumnDuplication( cols, join.getPropertyIterator() );
		}
	}
