	@Override
	public boolean test(Class<?> candidate) {
		//please do not collapse into single return
		if (isAbstract(candidate)) {
			return false;
		}
		if (candidate.isLocalClass()) {
			return false;
		}
		if (candidate.isAnonymousClass()) {
			return false;
		}
		return (isStatic(candidate) || !candidate.isMemberClass());
	}
