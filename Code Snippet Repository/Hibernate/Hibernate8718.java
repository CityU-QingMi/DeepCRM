	@Override
	public void startingComposite(CompositionDefinition compositionDefinition) {
		System.out.println(
				String.format(
						"%s Starting composite (%s)",
						StringHelper.repeat( ">>", ++depth ),
						compositionDefinition.getName()
				)
		);
	}
