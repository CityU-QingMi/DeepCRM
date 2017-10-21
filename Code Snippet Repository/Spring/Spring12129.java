		private static Set<HttpMethod> initAllowedHttpMethods(Set<String> declaredMethods) {
			Set<HttpMethod> result = new LinkedHashSet<>(declaredMethods.size());
			if (declaredMethods.isEmpty()) {
				for (HttpMethod method : HttpMethod.values()) {
					if (!HttpMethod.TRACE.equals(method)) {
						result.add(method);
					}
				}
			}
			else {
				boolean hasHead = declaredMethods.contains("HEAD");
				for (String method : declaredMethods) {
					result.add(HttpMethod.valueOf(method));
					if (!hasHead && "GET".equals(method)) {
						result.add(HttpMethod.HEAD);
					}
				}
			}
			return result;
		}
