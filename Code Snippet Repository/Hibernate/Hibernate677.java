	public void processSecondPasses(MetadataBuildingContext buildingContext) {
		inSecondPass = true;

		try {
			processSecondPasses( implicitColumnNamingSecondPassList );

			processSecondPasses( pkDrivenByDefaultMapsIdSecondPassList );
			processSecondPasses( setSimpleValueTypeSecondPassList );

			processCopyIdentifierSecondPassesInOrder();

			processFkSecondPassesInOrder();

			processSecondPasses( createKeySecondPasList );
			processSecondPasses( secondaryTableSecondPassList );

			processSecondPasses( querySecondPassList );
			processSecondPasses( generalSecondPassList );

			processPropertyReferences();

			secondPassCompileForeignKeys( buildingContext );

			processUniqueConstraintHolders( buildingContext );
			processJPAIndexHolders( buildingContext );

			processNaturalIdUniqueKeyBinders();

			processCachingOverrides();
		}
		finally {
			inSecondPass = false;
		}
	}
