	@Override
	public boolean matches(int pathIndex, MatchingContext matchingContext) {
		// No need to handle 'match start' checking as this captures everything
		// anyway and cannot be followed by anything else
		// assert next == null

		// If there is more data, it must start with the separator
		if (pathIndex < matchingContext.pathLength && !matchingContext.isSeparator(pathIndex)) {
			return false;
		}
		if (matchingContext.determineRemainingPath) {
			matchingContext.remainingPathIndex = matchingContext.pathLength;
		}
		if (matchingContext.extractingVariables) {
			// Collect the parameters from all the remaining segments
			MultiValueMap<String,String> parametersCollector = null;
			for (int i = pathIndex; i < matchingContext.pathLength; i++) {
				Element element = matchingContext.pathElements.get(i);
				if (element instanceof PathSegment) {
					MultiValueMap<String, String> parameters = ((PathSegment) element).parameters();
					if (!parameters.isEmpty()) {
						if (parametersCollector == null) {
							parametersCollector = new LinkedMultiValueMap<>();
						}
						parametersCollector.addAll(parameters);
					}
				}
			}
			matchingContext.set(variableName, pathToString(pathIndex, matchingContext.pathElements),
					parametersCollector == null?NO_PARAMETERS:parametersCollector);
		}
		return true;
	}
