	private void storeFilter(Filter<?> filter) {
		if (filter instanceof EngineFilter) {
			this.engineFilters.add((EngineFilter) filter);
		}
		else if (filter instanceof PostDiscoveryFilter) {
			this.postDiscoveryFilters.add((PostDiscoveryFilter) filter);
		}
		else if (filter instanceof DiscoveryFilter<?>) {
			this.discoveryFilters.add((DiscoveryFilter<?>) filter);
		}
		else {
			throw new PreconditionViolationException(
				String.format("Filter [%s] must implement %s, %s, or %s.", filter, EngineFilter.class.getSimpleName(),
					PostDiscoveryFilter.class.getSimpleName(), DiscoveryFilter.class.getSimpleName()));
		}
	}
