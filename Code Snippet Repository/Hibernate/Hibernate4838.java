	@Override
	public void handleException(CommandAcceptanceException exception) {
		throw new SchemaManagementException(
				String.format(
						Locale.ROOT,
						"Halting on error : %s",
						exception.getMessage()
				),
				exception
		);
	}
