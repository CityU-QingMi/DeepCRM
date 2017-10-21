		@Override
		public void handleEvent(StreamSourceChannel channel) {
			if (this.session.isDisconnected()) {
				if (logger.isDebugEnabled()) {
					logger.debug("SockJS sockJsSession closed, closing response.");
				}
				IoUtils.safeClose(this.connection);
				throw new SockJsException("Session closed.", this.session.getId(), null);
			}

			PooledByteBuffer pooled = bufferPool.allocate();
			try {
				int r;
				do {
					ByteBuffer buffer = pooled.getBuffer();
					buffer.clear();
					r = channel.read(buffer);
					buffer.flip();
					if (r == 0) {
						return;
					}
					else if (r == -1) {
						onSuccess();
					}
					else {
						while (buffer.hasRemaining()) {
							int b = buffer.get();
							if (b == '\n') {
								handleFrame();
							}
							else {
								this.outputStream.write(b);
							}
						}
					}
				}
				while (r > 0);
			}
			catch (IOException exc) {
				onFailure(exc);
			}
			finally {
				pooled.close();
			}
		}
