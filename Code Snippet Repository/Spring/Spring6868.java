	@Override
	public Destination resolveDestinationName(@Nullable Session session, String destinationName, boolean pubSubDomain)
			throws JMSException {

		Assert.state(this.beanFactory != null, "BeanFactory is required");
		try {
			return this.beanFactory.getBean(destinationName, Destination.class);
		}
		catch (BeansException ex) {
			throw new DestinationResolutionException(
					"Failed to look up Destinaton bean with name '" + destinationName + "'", ex);
		}
	}
