	public static ClassLoader index(ClassLoader classLoader, Resource... resources) {
		return new CandidateComponentsTestClassLoader(classLoader,
				Collections.enumeration(Stream.of(resources).map(r -> {
					try {
						return r.getURL();
					}
					catch (Exception ex) {
						throw new IllegalArgumentException("Invalid resource " + r, ex);
					}
				}).collect(Collectors.toList())));
	}
