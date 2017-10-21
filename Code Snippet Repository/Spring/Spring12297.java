	@Nullable
	public final String getForLookupPath(String lookupPath) {
		if (logger.isTraceEnabled()) {
			logger.trace("Getting resource URL for lookup path \"" + lookupPath + "\"");
		}

		List<String> matchingPatterns = new ArrayList<>();
		for (String pattern : this.handlerMap.keySet()) {
			if (getPathMatcher().match(pattern, lookupPath)) {
				matchingPatterns.add(pattern);
			}
		}

		if (!matchingPatterns.isEmpty()) {
			Comparator<String> patternComparator = getPathMatcher().getPatternComparator(lookupPath);
			Collections.sort(matchingPatterns, patternComparator);
			for (String pattern : matchingPatterns) {
				String pathWithinMapping = getPathMatcher().extractPathWithinPattern(pattern, lookupPath);
				String pathMapping = lookupPath.substring(0, lookupPath.indexOf(pathWithinMapping));
				if (logger.isTraceEnabled()) {
					logger.trace("Invoking ResourceResolverChain for URL pattern \"" + pattern + "\"");
				}
				ResourceHttpRequestHandler handler = this.handlerMap.get(pattern);
				ResourceResolverChain chain = new DefaultResourceResolverChain(handler.getResourceResolvers());
				String resolved = chain.resolveUrlPath(pathWithinMapping, handler.getLocations());
				if (resolved == null) {
					continue;
				}
				if (logger.isTraceEnabled()) {
					logger.trace("Resolved public resource URL path \"" + resolved + "\"");
				}
				return pathMapping + resolved;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("No matching resource mapping for lookup path \"" + lookupPath + "\"");
		}
		return null;
	}
