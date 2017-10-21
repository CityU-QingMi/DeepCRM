	private void configureRejectionPolicy(Element element, BeanDefinitionBuilder builder) {
		String rejectionPolicy = element.getAttribute("rejection-policy");
		if (!StringUtils.hasText(rejectionPolicy)) {
			return;
		}
		String prefix = "java.util.concurrent.ThreadPoolExecutor.";
		String policyClassName;
		if (rejectionPolicy.equals("ABORT")) {
			policyClassName = prefix + "AbortPolicy";
		}
		else if (rejectionPolicy.equals("CALLER_RUNS")) {
			policyClassName = prefix + "CallerRunsPolicy";
		}
		else if (rejectionPolicy.equals("DISCARD")) {
			policyClassName = prefix + "DiscardPolicy";
		}
		else if (rejectionPolicy.equals("DISCARD_OLDEST")) {
			policyClassName = prefix + "DiscardOldestPolicy";
		}
		else {
			policyClassName = rejectionPolicy;
		}
		builder.addPropertyValue("rejectedExecutionHandler", new RootBeanDefinition(policyClassName));
	}
