	@Nullable
	public PathRemainingMatchInfo matchStartOfPath(PathContainer pathContainer) {
		if (this.head == null) {
			return new PathRemainingMatchInfo(pathContainer);
		}
		else if (!hasLength(pathContainer)) {
			return null;
		}

		MatchingContext matchingContext = new MatchingContext(pathContainer, true);
		matchingContext.setMatchAllowExtraPath();
		boolean matches = this.head.matches(0, matchingContext);
		if (!matches) {
			return null;
		}
		else {
			PathRemainingMatchInfo info;
			if (matchingContext.remainingPathIndex == pathContainer.elements().size()) {
				info = new PathRemainingMatchInfo(EMPTY_PATH, matchingContext.getPathMatchResult());
			}
			else {
				info = new PathRemainingMatchInfo(pathContainer.subPath(matchingContext.remainingPathIndex),
						matchingContext.getPathMatchResult());
			}
			return info;
		}
	}
