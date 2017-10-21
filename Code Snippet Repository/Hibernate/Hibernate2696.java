	private FromElement locateSingleFromElement() {
		List fromElements = getWalker().getCurrentFromClause().getFromElements();
		if (fromElements == null || fromElements.size() != 1) {
			// TODO : should this be an error?
			return null;
		}
		FromElement element = (FromElement) fromElements.get(0);
		if (element.getClassAlias() != null) {
			// naked property-refs cannot be used with an aliased from element
			return null;
		}
		return element;
	}
