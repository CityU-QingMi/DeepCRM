		@Override
		public void run() {
			try {
				for (int i = 0; i < 100; i++) {
					performSet();
				}
			}
			catch (Throwable e) {
				test.ex = e;
			}
			finally {
				synchronized (test) {
					test.set.remove(this);
					test.notifyAll();
				}
			}
		}
