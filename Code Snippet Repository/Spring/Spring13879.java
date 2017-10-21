		@Override
		public ListenableFuture<Void> send(Message<byte[]> message) {
			updateLastWriteTime();
			SettableListenableFuture<Void> future = new SettableListenableFuture<>();
			try {
				WebSocketSession session = this.session;
				Assert.state(session != null, "No WebSocketSession available");
				session.sendMessage(this.codec.encode(message, session.getClass()));
				future.set(null);
			}
			catch (Throwable ex) {
				future.setException(ex);
			}
			finally {
				updateLastWriteTime();
			}
			return future;
		}
