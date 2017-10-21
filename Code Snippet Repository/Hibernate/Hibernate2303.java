	@Override
	public String toString() {
		String result = "CollectionEntry" +
				MessageHelper.collectionInfoString( loadedPersister.getRole(), loadedKey );
		if ( currentPersister != null ) {
			result += "->" +
					MessageHelper.collectionInfoString( currentPersister.getRole(), currentKey );
		}
		return result;
	}
