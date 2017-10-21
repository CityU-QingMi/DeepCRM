	@Override
	protected void configureBootstrapServiceRegistryBuilder(BootstrapServiceRegistryBuilder bsrb) {
		super.configureBootstrapServiceRegistryBuilder( bsrb );

		// Let's tell Hibernate to treat MutableState2 as immutable
		JavaTypeDescriptorRegistry.INSTANCE.addDescriptor(
				new JavaTypeDescriptorRegistry.FallbackJavaTypeDescriptor( MutableState2.class ) {
					@Override
					public MutabilityPlan getMutabilityPlan() {
						return ImmutableMutabilityPlan.INSTANCE;
					}
				}
		);
	}
