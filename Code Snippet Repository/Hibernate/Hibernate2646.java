	public String getDisplayText() {
		return "FromClause{" +
				"level=" + level +
				", fromElementCounter=" + fromElementCounter +
				", fromElements=" + fromElements.size() +
				", fromElementByClassAlias=" + fromElementByClassAlias.keySet() +
				", fromElementByTableAlias=" + fromElementByTableAlias.keySet() +
				", fromElementsByPath=" + fromElementsByPath.keySet() +
				", collectionJoinFromElementsByPath=" + collectionJoinFromElementsByPath.keySet() +
				", impliedElements=" + impliedElements +
				"}";
	}
