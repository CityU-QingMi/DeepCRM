	@Override
	public boolean endRead() {
		setInitialized();
		array = Array.newInstance( elementClass, tempList.size() );
		for ( int i=0; i<tempList.size(); i++ ) {
			Array.set( array, i, tempList.get( i ) );
		}
		tempList = null;
		return true;
	}
