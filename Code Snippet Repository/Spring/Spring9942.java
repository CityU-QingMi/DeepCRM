	@Nullable
	public PathMatchInfo matchAndExtract(PathContainer pathContainer) {
		if (this.head == null) {
			return hasLength(pathContainer) &&
				!(this.matchOptionalTrailingSeparator && pathContainerIsJustSeparator(pathContainer))
				? null : PathMatchInfo.EMPTY;
		}
		else if (!hasLength(pathContainer)) {
			if (this.head instanceof WildcardTheRestPathElement || this.head instanceof CaptureTheRestPathElement) {
				pathContainer = EMPTY_PATH; // Will allow CaptureTheRest to bind the variable to empty
			}
			else {
				return null;
			}
		}
		MatchingContext matchingContext = new MatchingContext(pathContainer, true);
		return this.head.matches(0, matchingContext) ? matchingContext.getPathMatchResult() : null;
	}
