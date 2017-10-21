	private void checkColumnDuplication() throws MappingException {
		HashSet cols = new HashSet();
		checkColumnDuplication( cols, getKey() );
		if ( isIndexed() ) {
			checkColumnDuplication(
					cols,
					( (IndexedCollection) this ).getIndex()
			);
		}
		if ( isIdentified() ) {
			checkColumnDuplication(
					cols,
					( (IdentifierCollection) this ).getIdentifier()
			);
		}
		if ( !isOneToMany() ) {
			checkColumnDuplication( cols, getElement() );
		}
	}
