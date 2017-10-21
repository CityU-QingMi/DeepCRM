		@Override
		public void run() {
			if (this.acceptOnExecution) {
				this.workListener.workAccepted(new WorkEvent(this, WorkEvent.WORK_ACCEPTED, work, null));
			}
			synchronized (this.monitor) {
				this.started = true;
				this.monitor.notify();
			}
			this.workListener.workStarted(new WorkEvent(this, WorkEvent.WORK_STARTED, this.work, null));
			try {
				this.work.run();
			}
			catch (RuntimeException | Error ex) {
				this.workListener.workCompleted(
						new WorkEvent(this, WorkEvent.WORK_COMPLETED, this.work, new WorkCompletedException(ex)));
				throw ex;
			}
			this.workListener.workCompleted(new WorkEvent(this, WorkEvent.WORK_COMPLETED, this.work, null));
		}
