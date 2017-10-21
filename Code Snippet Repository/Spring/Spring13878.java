		private void cancelInactivityTasks() {
			for (ScheduledFuture<?> task : this.inactivityTasks) {
				try {
					task.cancel(true);
				}
				catch (Throwable ex) {
					// Ignore
				}
			}
			this.lastReadTime = -1;
			this.lastWriteTime = -1;
			this.inactivityTasks.clear();
		}
