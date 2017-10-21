	public Iterator getSubclassIterator() {
		Iterator[] iters = new Iterator[subclasses.size() + 1];
		Iterator iter = subclasses.iterator();
		int i = 0;
		while ( iter.hasNext() ) {
			iters[i++] = ( (Subclass) iter.next() ).getSubclassIterator();
		}
		iters[i] = subclasses.iterator();
		return new JoinedIterator( iters );
	}
