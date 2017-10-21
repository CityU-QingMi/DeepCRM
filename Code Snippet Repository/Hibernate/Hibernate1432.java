	public JPAMetadataProvider(final MetadataBuildingOptions metadataBuildingOptions) {
		classLoaderAccess = new ClassLoaderAccessDelegateImpl() {
			ClassLoaderAccess delegate;

			@Override
			protected ClassLoaderAccess getDelegate() {
				if ( delegate == null ) {
					delegate = new ClassLoaderAccessImpl(
							metadataBuildingOptions.getTempClassLoader(),
							metadataBuildingOptions.getServiceRegistry().getService( ClassLoaderService.class )
					);
				}
				return delegate;
			}
		};

		xmlContext = new XMLContext( classLoaderAccess );

	}
