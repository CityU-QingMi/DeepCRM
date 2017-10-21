	@Override
	public boolean matches(int pathIndex, MatchingContext matchingContext) {
		String segmentData = null;
		// Assert if it exists it is a segment
		if (pathIndex < matchingContext.pathLength) {
			Element element = matchingContext.pathElements.get(pathIndex);
			if (!(element instanceof PathContainer.PathSegment)) {
				// Should not match a separator
				return false;
			}
			segmentData = ((PathContainer.PathSegment)element).valueToMatch();
			pathIndex++;
		}
		
		if (isNoMorePattern()) {
			if (matchingContext.determineRemainingPath) {
				matchingContext.remainingPathIndex = pathIndex;
				return true;
			}
			else {
				if (pathIndex == matchingContext.pathLength) {
					// and the path data has run out too
					return true;
				}
				else {
					return (matchingContext.isMatchOptionalTrailingSeparator() &&  // if optional slash is on...
							segmentData != null && segmentData.length() > 0 &&  // and there is at least one character to match the *...
							(pathIndex + 1) == matchingContext.pathLength &&   // and the next path element is the end of the candidate...
							matchingContext.isSeparator(pathIndex));  // and the final element is a separator
				}
			}
		}
		else { 
			// Within a path (e.g. /aa/*/bb) there must be at least one character to match the wildcard
			if (segmentData == null || segmentData.length() == 0) {
				return false;
			}
			return (this.next != null && this.next.matches(pathIndex, matchingContext));
		}
	}
