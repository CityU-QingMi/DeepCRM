	public CallbacksSecurityTests() {
		// setup security
		if (System.getSecurityManager() == null) {
			Policy policy = Policy.getPolicy();
			URL policyURL = getClass()
					.getResource(
							"/org/springframework/beans/factory/support/security/policy.all");
			System.setProperty("java.security.policy", policyURL.toString());
			System.setProperty("policy.allowSystemProperty", "true");
			policy.refresh();

			System.setSecurityManager(new SecurityManager());
		}
	}
