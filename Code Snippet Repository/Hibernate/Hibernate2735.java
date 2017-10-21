	private void addCollectionFromElement(FromElement fromElement) {
		if ( fromElement.isFetch() ) {
			if ( fromElement.getQueryableCollection() != null ) {
				String suffix;
				if ( collectionFromElements == null ) {
					collectionFromElements = new ArrayList();
					suffix = VERSION2_SQL ? "__" : "0__";
				}
				else {
					suffix = Integer.toString( collectionFromElements.size() ) + "__";
				}
				collectionFromElements.add( fromElement );
				fromElement.setCollectionSuffix( suffix );
			}
		}
	}
