	@Override
	public void configure(Configuration cfg) {
		cfg.registerTypeOverride(
				new UUIDCharType() {
					@Override
					protected boolean registerUnderJavaType() {
						return true;
					}
				}
		);
	}
