	public Iterator getSubclassClosureIterator() {
		ArrayList iters = new ArrayList();
		iters.add( new SingletonIterator( this ) );
		Iterator iter = getSubclassIterator();
		while ( iter.hasNext() ) {
			PersistentClass clazz = (PersistentClass) iter.next();
			iters.add( clazz.getSubclassClosureIterator() );
		}
		return new JoinedIterator( iters );
	}
