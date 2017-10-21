	private boolean readAndPublish() throws IOException {
		long r;
		while ((r = demand) > 0) {
			T data = read();
			if (data != null) {
				if (r != Long.MAX_VALUE) {
					DEMAND_FIELD_UPDATER.addAndGet(this, -1L);
				}
				Assert.state(this.subscriber != null, "No subscriber");
				this.subscriber.onNext(data);
			}
			else {
				return true;
			}
		}
		return false;
	}
