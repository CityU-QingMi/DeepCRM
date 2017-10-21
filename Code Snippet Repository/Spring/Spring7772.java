		private SessionFactory getSessionFactory() {
			try {
				return this.sessionFactoryFuture.get();
			}
			catch (InterruptedException ex) {
				throw new IllegalStateException("Interrupted during initialization of Hibernate SessionFactory: " +
						ex.getMessage());
			}
			catch (ExecutionException ex) {
				throw new IllegalStateException("Failed to asynchronously initialize Hibernate SessionFactory: " +
						ex.getMessage(), ex.getCause());
			}
		}
