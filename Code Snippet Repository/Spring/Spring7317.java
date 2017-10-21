	@Override
	@Nullable
	public SimpUser getUser(String userName) {
		// Prefer remote registries due to cross-server SessionLookup
		for (UserRegistrySnapshot registry : this.remoteRegistries.values()) {
			SimpUser user = registry.getUserMap().get(userName);
			if (user != null) {
				return user;
			}
		}
		return this.localRegistry.getUser(userName);
	}
