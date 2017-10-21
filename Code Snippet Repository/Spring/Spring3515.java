		@Override
		public void stop(final Runnable callback) {
			// calling stop() before the delay to preserve
			// invocation order in the 'stoppedBeans' list
			stop();
			final int delay = this.shutdownDelay;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(delay);
					}
					catch (InterruptedException e) {
						// ignore
					}
					finally {
						callback.run();
					}
				}
			}).start();
		}
