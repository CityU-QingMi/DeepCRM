	public Type getDataType() {
		if ( persister == null ) {
			if ( queryableCollection == null ) {
				return null;
			}
			return queryableCollection.getType();
		}
		else {
			return entityType;
		}
	}
