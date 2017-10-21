	public Object getChangedElement() {
		if ( changedElement instanceof Pair ) {
			return ( (Pair) changedElement ).getSecond();
		}

		if ( changedElement instanceof Map.Entry ) {
			return ( (Map.Entry) changedElement ).getValue();
		}

		return changedElement;
	}
