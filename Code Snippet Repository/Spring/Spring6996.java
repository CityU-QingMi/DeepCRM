	@Test
	public void testSessionCallback() throws Exception {
		JmsTemplate template = createTemplate();
		template.setConnectionFactory(this.connectionFactory);

		template.execute(new SessionCallback<Void>() {
			@Override
			public Void doInJms(Session session) throws JMSException {
				session.getTransacted();
				return null;
			}
		});

		verify(this.session).close();
		verify(this.connection).close();
	}
