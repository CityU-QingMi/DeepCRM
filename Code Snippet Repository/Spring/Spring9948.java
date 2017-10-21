	@Override
	public boolean matches(int pathIndex, MatchingContext matchingContext) {
		String textToMatch = matchingContext.pathElementValue(pathIndex);		
		Matcher matcher = this.pattern.matcher(textToMatch);
		boolean matches = matcher.matches();

		if (matches) {
			if (isNoMorePattern()) {
				if (matchingContext.determineRemainingPath && 
					((this.variableNames.size() == 0) ? true : textToMatch.length() > 0)) {
					matchingContext.remainingPathIndex = pathIndex + 1;
					matches = true;
				}
				else {
					// No more pattern, is there more data?
					// If pattern is capturing variables there must be some actual data to bind to them
					matches = (pathIndex + 1) >= matchingContext.pathLength &&
							  (this.variableNames.isEmpty() || textToMatch.length() > 0);
					if (!matches && matchingContext.isMatchOptionalTrailingSeparator()) {
						matches = (this.variableNames.isEmpty() || textToMatch.length() > 0) &&
							      (pathIndex + 2) >= matchingContext.pathLength &&
							      matchingContext.isSeparator(pathIndex + 1);
					}
				}
			}
			else {
				matches = (this.next != null && this.next.matches(pathIndex + 1, matchingContext));
			}
		}

		if (matches && matchingContext.extractingVariables) {
			// Process captures
			if (this.variableNames.size() != matcher.groupCount()) { // SPR-8455
				throw new IllegalArgumentException("The number of capturing groups in the pattern segment "
						+ this.pattern + " does not match the number of URI template variables it defines, "
						+ "which can occur if capturing groups are used in a URI template regex. "
						+ "Use non-capturing groups instead.");
			}
			for (int i = 1; i <= matcher.groupCount(); i++) {
				String name = this.variableNames.get(i - 1);
				String value = matcher.group(i);
				matchingContext.set(name, value,
						(i == this.variableNames.size())?
								((PathSegment)matchingContext.pathElements.get(pathIndex)).parameters():
								NO_PARAMETERS);
			}
		}
		return matches;
	}
