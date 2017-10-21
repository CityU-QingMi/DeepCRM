	@Override
	@SuppressWarnings("")
	public void sendError(int sc, String msg) throws IOException {
		copyBodyToResponse(false);
		try {
			super.sendError(sc, msg);
		}
		catch (IllegalStateException ex) {
			// Possibly on Tomcat when called too late: fall back to silent setStatus
			super.setStatus(sc, msg);
		}
		this.statusCode = sc;
	}
