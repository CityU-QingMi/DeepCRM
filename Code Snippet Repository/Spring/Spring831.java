	public void registerContainedBean(String containedBeanName, String containingBeanName) {
		// A quick check for an existing entry upfront, avoiding synchronization...
		Set<String> containedBeans = this.containedBeanMap.get(containingBeanName);
		if (containedBeans != null && containedBeans.contains(containedBeanName)) {
			return;
		}

		// No entry yet -> fully synchronized manipulation of the containedBeans Set
		synchronized (this.containedBeanMap) {
			containedBeans = this.containedBeanMap.get(containingBeanName);
			if (containedBeans == null) {
				containedBeans = new LinkedHashSet<>(8);
				this.containedBeanMap.put(containingBeanName, containedBeans);
			}
			containedBeans.add(containedBeanName);
		}
		registerDependentBean(containedBeanName, containingBeanName);
	}
