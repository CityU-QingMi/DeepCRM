	public boolean matches(PathContainer pathContainer) {
		if (this.head == null) {
			return !hasLength(pathContainer) || 
				(this.matchOptionalTrailingSeparator && pathContainerIsJustSeparator(pathContainer));
		}
		else if (!hasLength(pathContainer)) {
			if (this.head instanceof WildcardTheRestPathElement || this.head instanceof CaptureTheRestPathElement) {
				pathContainer = EMPTY_PATH; // Will allow CaptureTheRest to bind the variable to empty
			}
			else {
				return false;
			}
		}
		MatchingContext matchingContext = new MatchingContext(pathContainer, false);
		return this.head.matches(0, matchingContext);
	}
