	public Object getChangedElementIndex() {
		if ( changedElement instanceof Pair ) {
			return ( (Pair) changedElement ).getFirst();
		}

		if ( changedElement instanceof Map.Entry ) {
			return ( (Map.Entry) changedElement ).getKey();
		}

		return null;
	}
