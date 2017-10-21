	public ELContextImpl createELContext(JspContext context) {
		if (context == null) {
			throw new IllegalArgumentException("JspContext was null");
		}

		// create ELContext for JspContext
		ELResolver r = this.createELResolver();
		ELContextImpl ctx = new ELContextImpl(r);
		ctx.putContext(JspContext.class, context);

		// alert all ELContextListeners
		ELContextEvent event = new ELContextEvent(ctx);
		for (int i = 0; i < this.contextListeners.size(); i++) {
			this.contextListeners.get(i).contextCreated(event);
		}

		return ctx;
	}
