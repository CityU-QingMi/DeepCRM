	@Nullable
	static Cache extractFrom(Collection<? extends Cache> caches) {
		if (CollectionUtils.isEmpty(caches)) {
			return null;
		}
		else if (caches.size() == 1) {
			return caches.iterator().next();
		}
		else {
			throw new IllegalStateException("Unsupported cache resolution result " + caches +
					": JSR-107 only supports a single cache.");
		}
	}
