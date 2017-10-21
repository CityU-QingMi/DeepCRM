		private static Set<HttpMethod> initAllowedHttpMethods(Set<HttpMethod> declaredMethods) {
			if (declaredMethods.isEmpty()) {
				return EnumSet.allOf(HttpMethod.class).stream()
						.filter(method -> !method.equals(HttpMethod.TRACE))
						.collect(Collectors.toSet());
			}
			else {
				Set<HttpMethod> result = new LinkedHashSet<>(declaredMethods);
				if (result.contains(HttpMethod.GET)) {
					result.add(HttpMethod.HEAD);
				}
				return result;
			}
		}
