	@Override
	public String getSubscriptionName() {
		Object delegate = getDelegate();
		if (delegate != this && delegate instanceof SubscriptionNameProvider) {
			return ((SubscriptionNameProvider) delegate).getSubscriptionName();
		}
		else {
			return delegate.getClass().getName();
		}
	}
