		public void clear() {
			if (this.count == 0) {
				return;
			}
			lock();
			try {
				this.references = createReferenceArray(this.initialSize);
				this.resizeThreshold = (int) (this.references.length * getLoadFactor());
				this.count = 0;
			}
			finally {
				unlock();
			}
		}
