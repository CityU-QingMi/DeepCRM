	private Operator findOperator(SpelNode node) {
		if (node instanceof Operator) {
			return (Operator) node;
		}
		int childCount = node.getChildCount();
		for (int i = 0; i < childCount; i++) {
			Operator possible = findOperator(node.getChild(i));
			if (possible != null) {
				return possible;
			}
		}
		return null;
	}
