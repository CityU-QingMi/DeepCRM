	@CustomParameterized.Order(1)
	@Parameterized.Parameters(name = "")
	public List<Object[]> getCacheModeParameters() {
		ArrayList<Object[]> modes = new ArrayList<>();
		for (AccessType accessType : new AccessType[] {
				AccessType.TRANSACTIONAL,
				AccessType.READ_ONLY,
				AccessType.READ_WRITE
		}) {
			modes.add(new Object[]{CacheMode.INVALIDATION_SYNC, accessType});
		}
		for (AccessType accessType : new AccessType[] {
				AccessType.READ_ONLY,
				AccessType.READ_WRITE,
				AccessType.NONSTRICT_READ_WRITE
		}) {
			modes.add(new Object[]{CacheMode.REPL_SYNC, accessType});
			modes.add(new Object[]{CacheMode.DIST_SYNC, accessType});
			if (canUseLocalMode()) {
				modes.add(new Object[]{CacheMode.LOCAL, accessType});
			}
		}
		if (canUseLocalMode()) {
			modes.add(new Object[]{CacheMode.LOCAL, AccessType.TRANSACTIONAL});
		}
		return modes;
	}
