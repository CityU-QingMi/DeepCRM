	@Nullable
	private RequestMethodsRequestCondition matchRequestMethod(String httpMethodValue) {
		HttpMethod httpMethod = HttpMethod.resolve(httpMethodValue);
		if (httpMethod != null) {
			for (RequestMethod method : getMethods()) {
				if (httpMethod.matches(method.name())) {
					return new RequestMethodsRequestCondition(method);
				}
			}
			if (httpMethod == HttpMethod.HEAD && getMethods().contains(RequestMethod.GET)) {
				return GET_CONDITION;
			}
		}
		return null;
	}
