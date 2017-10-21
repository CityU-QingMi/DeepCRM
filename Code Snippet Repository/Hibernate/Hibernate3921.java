	public Iterator getSubclassPropertyClosureIterator() {
		ArrayList iters = new ArrayList();
		iters.add( getPropertyClosureIterator() );
		iters.add( subclassProperties.iterator() );
		for ( int i = 0; i < subclassJoins.size(); i++ ) {
			Join join = (Join) subclassJoins.get( i );
			iters.add( join.getPropertyIterator() );
		}
		return new JoinedIterator( iters );
	}
