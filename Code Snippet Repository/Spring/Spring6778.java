	protected MessageConsumer createConsumer(Session session, Destination destination) throws JMSException {
		if (isPubSubDomain() && destination instanceof Topic) {
			if (isSubscriptionShared()) {
				return (isSubscriptionDurable() ?
						session.createSharedDurableConsumer((Topic) destination, getSubscriptionName(), getMessageSelector()) :
						session.createSharedConsumer((Topic) destination, getSubscriptionName(), getMessageSelector()));
			}
			else if (isSubscriptionDurable()) {
				return session.createDurableSubscriber(
						(Topic) destination, getSubscriptionName(), getMessageSelector(), isPubSubNoLocal());
			}
			else {
				// Only pass in the NoLocal flag in case of a Topic (pub-sub mode):
				// Some JMS providers, such as WebSphere MQ 6.0, throw IllegalStateException
				// in case of the NoLocal flag being specified for a Queue.
				return session.createConsumer(destination, getMessageSelector(), isPubSubNoLocal());
			}
		}
		else {
			return session.createConsumer(destination, getMessageSelector());
		}
	}
