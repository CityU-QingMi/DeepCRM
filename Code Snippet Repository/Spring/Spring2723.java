	public int aroundAdviceOne(ProceedingJoinPoint pjp) {
		int ret = -1;
		this.collaborator.aroundAdviceOne(this.name);
		try {
			ret = ((Integer)pjp.proceed()).intValue();
		}
		catch (Throwable t) {
			throw new RuntimeException(t);
		}
		this.collaborator.aroundAdviceOne(this.name);
		return ret;
	}
