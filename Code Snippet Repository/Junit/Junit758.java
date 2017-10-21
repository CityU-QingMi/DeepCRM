	@Override
	public boolean test(Class<?> candidate) {
		// Do not collapse into a single return statement.
		if (!isPublic(candidate)) {
			return false;
		}
		if (isAbstract(candidate)) {
			return false;
		}
		if (isInnerClass(candidate)) {
			return false;
		}

		return true;
	}
