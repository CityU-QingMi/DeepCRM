	@Override
	public final Iterator queuedAdditionIterator() {
		if ( hasQueuedOperations() ) {
			return new Iterator() {
				private int index;

				@Override
				public Object next() {
					return operationQueue.get( index++ ).getAddedInstance();
				}

				@Override
				public boolean hasNext() {
					return index < operationQueue.size();
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
		else {
			return EmptyIterator.INSTANCE;
		}
	}
