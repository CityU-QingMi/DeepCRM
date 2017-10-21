	@Override
	public void evaluate() throws Throwable {
		if (this.timeout == 0) {
			this.next.evaluate();
		}
		else {
			long startTime = System.currentTimeMillis();
			try {
				this.next.evaluate();
			}
			finally {
				long elapsed = System.currentTimeMillis() - startTime;
				if (elapsed > this.timeout) {
					throw new TimeoutException(
							String.format("Test took %s ms; limit was %s ms.", elapsed, this.timeout));
				}
			}
		}
	}
