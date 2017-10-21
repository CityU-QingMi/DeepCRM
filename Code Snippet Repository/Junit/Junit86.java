	@Override
	public boolean test(Class<?> candidate) {
		// Do not collapse into a single return statement.
		if (isPrivate(candidate)) {
			return false;
		}
		if (!isInnerClass(candidate)) {
			return false;
		}

		return true;
	}
