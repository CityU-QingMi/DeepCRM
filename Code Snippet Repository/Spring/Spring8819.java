	@Override
	@Nullable
	public <T> T execute(final InteractionCallback<T> action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");
		return execute((ConnectionCallback<T>) (connection, connectionFactory) -> {
			Interaction interaction = connection.createInteraction();
			try {
				return action.doInInteraction(interaction, connectionFactory);
			}
			finally {
				closeInteraction(interaction);
			}
		});
	}
