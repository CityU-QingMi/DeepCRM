	@Override
	public boolean setStatusCode(@Nullable HttpStatus statusCode) {
		if (this.state.get() == State.COMMITTED) {
			if (logger.isDebugEnabled()) {
				logger.debug("Can't set the status " + (statusCode != null ? statusCode.toString() : "null") +
						" because the HTTP response has already been committed");
			}
			return false;
		}
		else {
			this.statusCode = statusCode;
			return true;
		}
	}
