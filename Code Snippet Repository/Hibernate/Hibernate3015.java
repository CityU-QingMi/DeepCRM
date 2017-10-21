	public boolean isLookupByNaturalKey() {
		if ( projection != null ) {
			return false;
		}
		if ( subcriteriaList.size() > 0 ) {
			return false;
		}
		if ( criterionEntries.size() != 1 ) {
			return false;
		}
		CriterionEntry ce = criterionEntries.get(0);
		return ce.getCriterion() instanceof NaturalIdentifier;
	}
