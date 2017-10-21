	@Override
	@Nullable
	public ProducesRequestCondition getMatchingCondition(HttpServletRequest request) {
		if (CorsUtils.isPreFlightRequest(request)) {
			return PRE_FLIGHT_MATCH;
		}
		if (isEmpty()) {
			return this;
		}
		List<MediaType> acceptedMediaTypes;
		try {
			acceptedMediaTypes = getAcceptedMediaTypes(request);
		}
		catch (HttpMediaTypeException ex) {
			return null;
		}
		Set<ProduceMediaTypeExpression> result = new LinkedHashSet<>(expressions);
		for (Iterator<ProduceMediaTypeExpression> iterator = result.iterator(); iterator.hasNext();) {
			ProduceMediaTypeExpression expression = iterator.next();
			if (!expression.match(acceptedMediaTypes)) {
				iterator.remove();
			}
		}
		if (!result.isEmpty()) {
			return new ProducesRequestCondition(result, this.contentNegotiationManager);
		}
		else if (acceptedMediaTypes.contains(MediaType.ALL)) {
			return EMPTY_CONDITION;
		}
		else {
			return null;
		}
	}
