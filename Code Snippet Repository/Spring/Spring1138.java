		@Override
		public void run() {
			try {
				for (int i = 0; i < 10000; i++) {
					performTest();
				}
			}
			catch (Throwable e) {
				ex = e;
			}
			finally {
				synchronized (set) {
					set.remove(this);
					set.notifyAll();
				}
			}
		}
