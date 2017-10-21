		@Override
		public void onWriteInactivity(final Runnable runnable, final long duration) {
			Assert.state(getTaskScheduler() != null, "No TaskScheduler configured");
			this.lastWriteTime = System.currentTimeMillis();
			this.inactivityTasks.add(getTaskScheduler().scheduleWithFixedDelay(new Runnable() {
				@Override
				public void run() {
					if (System.currentTimeMillis() - lastWriteTime > duration) {
						try {
							runnable.run();
						}
						catch (Throwable ex) {
							if (logger.isDebugEnabled()) {
								logger.debug("WriteInactivityTask failure", ex);
							}
						}
					}
				}
			}, duration / 2));
		}
