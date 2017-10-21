	@Test
	public void getAdapterForReactiveSubType() throws Exception {

		ReactiveAdapter adapter1 = getAdapter(Flux.class);
		ReactiveAdapter adapter2 = getAdapter(FluxProcessor.class);

		assertSame(adapter1, adapter2);

		this.registry.registerReactiveType(
				ReactiveTypeDescriptor.multiValue(FluxProcessor.class, FluxProcessor::empty),
				o -> (FluxProcessor<?, ?>) o,
				FluxProcessor::from);

		ReactiveAdapter adapter3 = getAdapter(FluxProcessor.class);

		assertNotNull(adapter3);
		assertNotSame(adapter1, adapter3);
	}
