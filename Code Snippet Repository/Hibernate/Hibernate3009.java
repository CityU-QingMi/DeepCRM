	@Override
	public String toString() {
		return "CriteriaImpl(" +
			entityOrClassName + ":" +
			(rootAlias==null ? "" : rootAlias) +
			subcriteriaList.toString() +
			criterionEntries.toString() +
			( projection==null ? "" : projection.toString() ) +
			')';
	}
