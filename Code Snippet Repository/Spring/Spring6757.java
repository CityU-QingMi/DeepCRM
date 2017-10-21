	protected void doSend(MessageProducer producer, Message message) throws JMSException {
		if (this.deliveryDelay >= 0) {
			producer.setDeliveryDelay(this.deliveryDelay);
		}
		if (isExplicitQosEnabled()) {
			producer.send(message, getDeliveryMode(), getPriority(), getTimeToLive());
		}
		else {
			producer.send(message);
		}
	}
