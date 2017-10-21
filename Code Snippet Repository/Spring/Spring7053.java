	@Test
	public void receiveTimeoutExpired() {
		JmsInvokerProxyFactoryBean pfb = new JmsInvokerProxyFactoryBean() {
			@Override
			protected Message doExecuteRequest(Session session, Queue queue, Message requestMessage) throws JMSException {
				return null; // faking no message received
			}
		};
		pfb.setServiceInterface(ITestBean.class);
		pfb.setConnectionFactory(this.mockConnectionFactory);
		pfb.setQueue(this.mockQueue);
		pfb.setReceiveTimeout(1500);
		pfb.afterPropertiesSet();
		ITestBean proxy = (ITestBean) pfb.getObject();

		thrown.expect(RemoteTimeoutException.class);
		thrown.expectMessage("1500 ms");
		thrown.expectMessage("getAge");
		proxy.getAge();
	}
