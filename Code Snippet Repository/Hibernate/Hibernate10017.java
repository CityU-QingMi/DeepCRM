	@Override
	@SuppressWarnings({""})
	protected Collection getNewCollectionContent(PersistentCollection newCollection) {
		if ( newCollection == null ) {
			return null;
		}
		else {
			return Tools.listToIndexElementPairList( (List<Object>) newCollection );
		}
	}
