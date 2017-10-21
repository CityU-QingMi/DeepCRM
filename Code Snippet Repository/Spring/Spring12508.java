	@Override
	public void setApplicationContext(ApplicationContext ctx) throws ApplicationContextException {
		// check reinitialization
		if (this.ac != null) {
			throw new IllegalStateException("Already initialized");
		}

		// check message source availability
		if (ctx != null) {
			try {
				ctx.getMessage("code1", null, Locale.getDefault());
			}
			catch (NoSuchMessageException ex) {
				// expected
			}
		}

		this.ac = ctx;
	}
