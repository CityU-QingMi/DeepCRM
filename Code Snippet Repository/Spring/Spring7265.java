		private void invoke(List<Runnable> callbacks) {
			for (Runnable runnable : callbacks) {
				try {
					runnable.run();
				}
				catch (Throwable ex) {
					// ignore
				}
			}
		}
