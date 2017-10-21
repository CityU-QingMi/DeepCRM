	@Nullable
	private RequestMethodsRequestCondition matchRequestMethod(@Nullable HttpMethod httpMethod) {
		if (httpMethod != null) {
			for (RequestMethod method : getMethods()) {
				if (httpMethod.matches(method.name())) {
					return new RequestMethodsRequestCondition(method);
				}
			}
			if (httpMethod == HttpMethod.HEAD && getMethods().contains(RequestMethod.GET)) {
				return HEAD_CONDITION;
			}
		}
		return null;
	}
