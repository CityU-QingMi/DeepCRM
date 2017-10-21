	@Around("")
	public Object retry(ProceedingJoinPoint jp) throws Throwable {
		boolean retry = true;
		Object o = null;
		while (retry) {
			try {
				retry = false;
				this.beginCalls++;
				try {
					o = jp.proceed();
					this.commitCalls++;
				}
				catch (RetryableException re) {
					this.rollbackCalls++;
					throw re;
				}
			}
			catch (RetryableException re) {
				retry = true;
			}
		}
		return o;
	}
