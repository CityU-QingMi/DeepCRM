	@Override
	public E next() {
		this.inUse = true;
		for (Iterator<E> iterator : this.iterators) {
			if (iterator.hasNext()) {
				return iterator.next();
			}
		}
		throw new NoSuchElementException("All iterators exhausted");
	}
