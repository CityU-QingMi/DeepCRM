	private ProtectionDomain getDomain() {
		final Class cl;
		if ( this.targetBean != null ) {
			cl = this.targetBean;
		}
		else {
			cl = this.getClass();
		}
		return cl.getProtectionDomain();
	}
