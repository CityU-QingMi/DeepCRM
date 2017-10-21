	@Override
	protected Collection getOldCollectionContent(Serializable oldCollection) {
		if ( oldCollection == null ) {
			return null;
		}
		else if ( oldCollection instanceof Map ) {
			return ( (Map) oldCollection ).keySet();
		}
		else {
			return (Collection) oldCollection;
		}
	}
