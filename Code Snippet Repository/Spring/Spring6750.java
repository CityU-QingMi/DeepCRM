	@Override
	@Nullable
	public <T> T browseSelected(final String queueName, @Nullable final String messageSelector, final BrowserCallback<T> action)
			throws JmsException {

		Assert.notNull(action, "Callback object must not be null");
		return execute(session -> {
			Queue queue = (Queue) getDestinationResolver().resolveDestinationName(session, queueName, false);
			QueueBrowser browser = createBrowser(session, queue, messageSelector);
			try {
				return action.doInJms(session, browser);
			}
			finally {
				JmsUtils.closeQueueBrowser(browser);
			}
		}, true);
	}
