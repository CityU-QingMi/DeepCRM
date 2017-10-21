	@Override
	public EntityManagerFactory getNativeEntityManagerFactory() {
		if (this.nativeEntityManagerFactory != null) {
			return this.nativeEntityManagerFactory;
		}
		else {
			Assert.state(this.nativeEntityManagerFactoryFuture != null, "No native EntityManagerFactory available");
			try {
				return this.nativeEntityManagerFactoryFuture.get();
			}
			catch (InterruptedException ex) {
				throw new IllegalStateException("Interrupted during initialization of native EntityManagerFactory: " +
						ex.getMessage());
			}
			catch (ExecutionException ex) {
				throw new IllegalStateException("Failed to asynchronously initialize native EntityManagerFactory: " +
						ex.getMessage(), ex.getCause());
			}
		}
	}
