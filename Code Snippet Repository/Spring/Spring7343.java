		@Override
		public void run() {
			Message<?> message = this.inputMessage;
			try {
				message = applyBeforeHandle(message);
				if (message == null) {
					return;
				}
				this.messageHandler.handleMessage(message);
				triggerAfterMessageHandled(message, null);
			}
			catch (Exception ex) {
				triggerAfterMessageHandled(message, ex);
				if (ex instanceof MessagingException) {
					throw (MessagingException) ex;
				}
				String description = "Failed to handle " + message + " to " + this + " in " + this.messageHandler;
				throw new MessageDeliveryException(message, description, ex);
			}
			catch (Throwable err) {
				String description = "Failed to handle " + message + " to " + this + " in " + this.messageHandler;
				MessageDeliveryException ex2 = new MessageDeliveryException(message, description, err);
				triggerAfterMessageHandled(message, ex2);
				throw ex2;
			}
		}
