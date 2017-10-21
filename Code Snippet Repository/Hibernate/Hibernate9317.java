	private void buildMetadata(String extraPhysicalTableTypes) {
		if ( extraPhysicalTableTypes == null ) {
			ssr = new StandardServiceRegistryBuilder().build();
		}
		else {
			ssr = new StandardServiceRegistryBuilder()
					.applySetting( Environment.EXTRA_PHYSICAL_TABLE_TYPES, extraPhysicalTableTypes )
					.build();
		}
		metadata = (MetadataImplementor) new MetadataSources( ssr )
				.buildMetadata();
		metadata.validate();
	}
