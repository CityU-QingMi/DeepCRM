	@Nullable
	private SpelNodeImpl eatPrimaryExpression() {
		SpelNodeImpl start = eatStartNode();  // always a start node
		List<SpelNodeImpl> nodes = null;
		SpelNodeImpl node = eatNode();
		while (node != null) {
			if (nodes == null) {
				nodes = new ArrayList<>(4);
				nodes.add(start);
			}
			nodes.add(node);
			node = eatNode();
		}
		if (start == null || nodes == null) {
			return start;
		}
		return new CompoundExpression(toPos(start.getStartPosition(),
				nodes.get(nodes.size() - 1).getEndPosition()),
				nodes.toArray(new SpelNodeImpl[nodes.size()]));
	}
