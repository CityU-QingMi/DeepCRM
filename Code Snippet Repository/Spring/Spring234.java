	@Override
	public Pointcut getPointcut() {
		synchronized (this.pointcutMonitor) {
			if (this.pointcut == null) {
				this.pointcut = createPointcut();
				if (this.patterns != null) {
					this.pointcut.setPatterns(this.patterns);
				}
			}
			return pointcut;
		}
	}
