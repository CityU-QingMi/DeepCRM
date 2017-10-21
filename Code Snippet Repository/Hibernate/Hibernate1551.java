	@Override
	public boolean needsRecreate(CollectionPersister persister) {
		// Workaround for situations like HHH-7072.  If the collection element is a component that consists entirely
		// of nullable properties, we currently have to forcefully recreate the entire collection.  See the use
		// of hasNotNullableColumns in the AbstractCollectionPersister constructor for more info.  In order to delete
		// row-by-row, that would require SQL like "WHERE ( COL = ? OR ( COL is null AND ? is null ) )", rather than
		// the current "WHERE COL = ?" (fails for null for most DBs).  Note that
		// the param would have to be bound twice.  Until we eventually add "parameter bind points" concepts to the
		// AST in ORM 5+, handling this type of condition is either extremely difficult or impossible.  Forcing
		// recreation isn't ideal, but not really any other option in ORM 4.
		// Selecting a type used in where part of update statement
		// (must match condidion in org.hibernate.persister.collection.BasicCollectionPersister.doUpdateRows).
		// See HHH-9474
		Type whereType;
		if ( persister.hasIndex() ) {
			whereType = persister.getIndexType();
		}
		else {
			whereType = persister.getElementType();
		}
		if ( whereType instanceof CompositeType ) {
			CompositeType componentIndexType = (CompositeType) whereType;
			return !componentIndexType.hasNotNullProperty();
		}
		return false;
	}
