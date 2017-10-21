	@Override
	public boolean test(Method candidate) {
		// Please do not collapse the following into a single statement.
		if (isStatic(candidate)) {
			return false;
		}
		if (isPrivate(candidate)) {
			return false;
		}
		if (isAbstract(candidate)) {
			return false;
		}
		if (returnsVoid(candidate) != this.mustReturnVoid) {
			return false;
		}

		return isAnnotated(candidate, this.annotationType);
	}
