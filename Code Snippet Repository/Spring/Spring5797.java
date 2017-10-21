	private List<MethodResolver> initMethodResolvers() {
		List<MethodResolver> resolvers = this.methodResolvers;
		if (resolvers == null) {
			resolvers = new ArrayList<>(1);
			this.reflectiveMethodResolver = new ReflectiveMethodResolver();
			resolvers.add(this.reflectiveMethodResolver);
			this.methodResolvers = resolvers;
		}
		return resolvers;
	}
