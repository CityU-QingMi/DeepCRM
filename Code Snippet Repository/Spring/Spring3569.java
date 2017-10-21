	@Override
	public void destroy() {
		this.properlyDestroyed = true;
		Thread thread = new Thread() {
			@Override
			public void run() {
				Assert.state(applicationContext.getBean("messageSource") instanceof StaticMessageSource,
						"Invalid MessageSource bean");
				try {
					applicationContext.getBean("service2");
					// Should have thrown BeanCreationNotAllowedException
					properlyDestroyed = false;
				}
				catch (BeanCreationNotAllowedException ex) {
					// expected
				}
			}
		};
		thread.start();
		try {
			thread.join();
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
