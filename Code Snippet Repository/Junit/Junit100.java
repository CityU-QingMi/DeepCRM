		@Override
		public Object get() {
			if (value == NO_VALUE_SET) {
				lock.lock();
				try {
					if (value == NO_VALUE_SET) {
						value = delegate.get();
					}
				}
				finally {
					lock.unlock();
				}
			}
			return value;
		}
