	public void doWait() {
		this.counter++;
		// wait until stop is called
		synchronized (this.lock) {
			try {
				this.lock.wait();
			}
			catch (InterruptedException e) {
				// fall through
			}
		}
	}
