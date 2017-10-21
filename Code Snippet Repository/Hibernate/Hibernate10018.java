	@Override
	@SuppressWarnings({""})
	protected Collection getOldCollectionContent(Serializable oldCollection) {
		if ( oldCollection == null ) {
			return null;
		}
		else {
			return Tools.listToIndexElementPairList( (List<Object>) oldCollection );
		}
	}
