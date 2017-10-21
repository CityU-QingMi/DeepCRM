	@Override
	public Set<SimpUser> getUsers() {
		// Prefer remote registries due to cross-server SessionLookup
		Set<SimpUser> result = new HashSet<>();
		for (UserRegistrySnapshot registry : this.remoteRegistries.values()) {
			result.addAll(registry.getUserMap().values());
		}
		result.addAll(this.localRegistry.getUsers());
		return result;
	}
