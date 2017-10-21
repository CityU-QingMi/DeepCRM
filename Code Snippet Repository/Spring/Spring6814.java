		@Override
		protected Message createMessageForPayload(Object payload, Session session, @Nullable Object conversionHint)
				throws JMSException {

			MessageConverter converter = getMessageConverter();
			if (converter == null) {
				throw new IllegalStateException("No message converter, cannot handle '" + payload + "'");
			}
			if (converter instanceof SmartMessageConverter) {
				return ((SmartMessageConverter) converter).toMessage(payload, session, conversionHint);

			}
			return converter.toMessage(payload, session);
		}
