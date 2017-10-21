	@Override
	public boolean canCompare(@Nullable Object left, @Nullable Object right) {
		if (left == null || right == null) {
			return true;
		}
		if (left instanceof Number && right instanceof Number) {
			return true;
		}
		if (left instanceof Comparable) {
			return true;
		}
		return false;
	}
