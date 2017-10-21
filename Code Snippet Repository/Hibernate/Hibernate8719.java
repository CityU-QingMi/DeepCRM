	@Override
	public void finishingComposite(CompositionDefinition compositionDefinition) {
		System.out.println(
				String.format(
						"%s Finishing composite (%s)",
						StringHelper.repeat( "<<", depth-- ),
						compositionDefinition.getName()
				)
		);
	}
