	@Override
	public void start(long timeout) {
		Assert.state(!isCompleted(), "Async processing has already completed");
		if (isStarted()) {
			return;
		}

		HttpServletRequest servletRequest = this.request.getServletRequest();
		HttpServletResponse servletResponse = this.response.getServletResponse();

		this.asyncContext = servletRequest.startAsync(servletRequest, servletResponse);
		this.asyncContext.addListener(this);

		if (timeout != NO_TIMEOUT_VALUE) {
			this.asyncContext.setTimeout(timeout);
		}
	}
