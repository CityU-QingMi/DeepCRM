	private void sendInternal(Object object, @Nullable MediaType mediaType) throws IOException {
		if (this.handler != null) {
			try {
				this.handler.send(object, mediaType);
			}
			catch (IOException ex) {
				throw ex;
			}
			catch (Throwable ex) {
				throw new IllegalStateException("Failed to send " + object, ex);
			}
		}
		else {
			this.earlySendAttempts.add(new DataWithMediaType(object, mediaType));
		}
	}
