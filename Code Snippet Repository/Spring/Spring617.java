	@Nullable
	public Object getObject(String key) {
		if (this.beanFactory.containsBean(key)) {
			return this.beanFactory.getBean(key);
		}
		else if (this.scope != null){
			return this.scope.resolveContextualObject(key);
		}
		else {
			return null;
		}
	}
