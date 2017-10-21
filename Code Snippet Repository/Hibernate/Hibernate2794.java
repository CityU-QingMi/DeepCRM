	private void reset(QueryTranslatorImpl q) {
		//join = q.createJoinFragment(useThetaStyleJoin);
		dotcount = 0;
		currentName = null;
		currentProperty = null;
		collectionName = null;
		collectionRole = null;
		componentPath.setLength( 0 );
		type = null;
		collectionName = null;
		columns = null;
		expectingCollectionIndex = false;
		continuation = false;
		currentPropertyMapping = null;
	}
