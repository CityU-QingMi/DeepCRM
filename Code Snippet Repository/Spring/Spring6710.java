	protected Session createSession(Connection con, Integer mode) throws JMSException {
		// Determine JMS API arguments...
		boolean transacted = (mode == Session.SESSION_TRANSACTED);
		int ackMode = (transacted ? Session.AUTO_ACKNOWLEDGE : mode);
		// Now actually call the appropriate JMS factory method...
		if (Boolean.FALSE.equals(this.pubSubMode) && con instanceof QueueConnection) {
			return ((QueueConnection) con).createQueueSession(transacted, ackMode);
		}
		else if (Boolean.TRUE.equals(this.pubSubMode) && con instanceof TopicConnection) {
			return ((TopicConnection) con).createTopicSession(transacted, ackMode);
		}
		else {
			return con.createSession(transacted, ackMode);
		}
	}
