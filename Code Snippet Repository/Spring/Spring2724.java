	public int aroundAdviceTwo(ProceedingJoinPoint pjp) {
		int ret = -1;
		this.collaborator.aroundAdviceTwo(this.name);
		try {
			ret = ((Integer)pjp.proceed()).intValue();
		}
		catch (Throwable t) {
			throw new RuntimeException(t);
		}
		this.collaborator.aroundAdviceTwo(this.name);
		return ret;
	}
