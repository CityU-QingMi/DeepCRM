		public Collection<Invalidator> getInvalidators() {
			lock.lock();
			try {
				if (singleInvalidator != null) {
					return Collections.singleton(singleInvalidator);
				}
				else if (invalidators != null) {
					return new ArrayList<Invalidator>(invalidators.values());
				}
				else {
					return Collections.EMPTY_LIST;
				}
			}
			finally {
				lock.unlock();
			}
		}
