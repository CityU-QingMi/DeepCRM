		@Override
		public void onMessage(Message message) {
			boolean applyDeliveryCalls = !hasBeforeDeliveryBeenCalled();
			if (applyDeliveryCalls) {
				try {
					beforeDelivery(null);
				}
				catch (ResourceException ex) {
					throw new JmsResourceException(ex);
				}
			}
			try {
				getMessageListener().onMessage(message);
			}
			catch (RuntimeException | Error ex) {
				onEndpointException(ex);
				throw ex;
			}
			finally {
				if (applyDeliveryCalls) {
					try {
						afterDelivery();
					}
					catch (ResourceException ex) {
						throw new JmsResourceException(ex);
					}
				}
			}
		}
