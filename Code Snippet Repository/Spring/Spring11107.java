	@Nullable
	private <T> T createSingleBean(Function<WebFluxConfigurer, T> factory, Class<T> beanType) {
		List<T> result = this.delegates.stream().map(factory).filter(t -> t != null).collect(Collectors.toList());
		if (result.isEmpty()) {
			return null;
		}
		else if (result.size() == 1) {
			return result.get(0);
		}
		else {
			throw new IllegalStateException("More than one WebFluxConfigurer implements " +
					beanType.getSimpleName() + " factory method.");
		}
	}
