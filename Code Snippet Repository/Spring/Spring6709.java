	protected void prepareConnection(Connection con) throws JMSException {
		if (getClientId() != null) {
			con.setClientID(getClientId());
		}
		if (this.aggregatedExceptionListener != null) {
			con.setExceptionListener(this.aggregatedExceptionListener);
		}
		else if (getExceptionListener() != null || isReconnectOnException()) {
			ExceptionListener listenerToUse = getExceptionListener();
			if (isReconnectOnException()) {
				this.aggregatedExceptionListener = new AggregatedExceptionListener();
				this.aggregatedExceptionListener.delegates.add(this);
				if (listenerToUse != null) {
					this.aggregatedExceptionListener.delegates.add(listenerToUse);
				}
				listenerToUse = this.aggregatedExceptionListener;
			}
			con.setExceptionListener(listenerToUse);
		}
	}
