	public final boolean match(HttpServletRequest request) {
		boolean isMatch;
		if (this.value != null) {
			isMatch = matchValue(request);
		}
		else {
			isMatch = matchName(request);
		}
		return (this.isNegated ? !isMatch : isMatch);
	}
