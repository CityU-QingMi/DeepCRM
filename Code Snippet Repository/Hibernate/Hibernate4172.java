	private String[] getSubclassNameClosureBySubclassTable(int subclassTableNumber) {
		final int index = subclassTableNumber - getTableSpan();

		if ( index > subclassNamesBySubclassTable.length ) {
			throw new IllegalArgumentException(
					"Given subclass table number is outside expected range [" + subclassNamesBySubclassTable.length
							+ "] as defined by subclassTableNameClosure/subclassClosure"
			);
		}

		return subclassNamesBySubclassTable[index];
	}
