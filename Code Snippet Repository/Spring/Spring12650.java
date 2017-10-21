	private List<HandlerInterceptor> getInterceptorsForPath(String lookupPath) {
		PathMatcher pathMatcher = new AntPathMatcher();
		List<HandlerInterceptor> result = new ArrayList<>();
		for (Object interceptor : this.registry.getInterceptors()) {
			if (interceptor instanceof MappedInterceptor) {
				MappedInterceptor mappedInterceptor = (MappedInterceptor) interceptor;
				if (mappedInterceptor.matches(lookupPath, pathMatcher)) {
					result.add(mappedInterceptor.getInterceptor());
				}
			}
			else if (interceptor instanceof HandlerInterceptor) {
				result.add((HandlerInterceptor) interceptor);
			}
			else {
				fail("Unexpected interceptor type: " + interceptor.getClass().getName());
			}
		}
		return result;
	}
