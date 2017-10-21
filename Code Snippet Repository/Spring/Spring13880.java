		@Override
		public void onReadInactivity(final Runnable runnable, final long duration) {
			Assert.state(getTaskScheduler() != null, "No TaskScheduler configured");
			this.lastReadTime = System.currentTimeMillis();
			this.inactivityTasks.add(getTaskScheduler().scheduleWithFixedDelay(() -> {
				if (System.currentTimeMillis() - lastReadTime > duration) {
					try {
						runnable.run();
					}
					catch (Throwable ex) {
						if (logger.isDebugEnabled()) {
							logger.debug("ReadInactivityTask failure", ex);
						}
					}
				}
			}, duration / 2));
		}
