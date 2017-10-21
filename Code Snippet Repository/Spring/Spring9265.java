	@Override
	public void close() {
		if (this.responseStream != null) {
			try {
				StreamUtils.drain(this.responseStream);
				this.responseStream.close();
			}
			catch (IOException ex) {
				// ignore
			}
		}
	}
