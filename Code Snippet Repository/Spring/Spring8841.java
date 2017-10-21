	@Override
	public void afterPropertiesSet() throws NamingException {
		if (this.workManager == null) {
			if (this.workManagerName != null) {
				this.workManager = lookup(this.workManagerName, WorkManager.class);
			}
			else {
				this.workManager = getDefaultWorkManager();
			}
		}
	}
