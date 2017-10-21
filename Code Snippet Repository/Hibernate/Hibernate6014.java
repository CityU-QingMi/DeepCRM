	private void doTest(List<ParsedPersistenceXmlDescriptor> parsedDescriptors,
			final String persistenceUnitName, final boolean shouldExclude) {
		for (final ParsedPersistenceXmlDescriptor descriptor : parsedDescriptors) {
			if (descriptor.getName().equals( persistenceUnitName )) {
				assertEquals(descriptor.isExcludeUnlistedClasses(), shouldExclude);
				return;
			}
		}
		fail("Could not find the persistence unit: " + persistenceUnitName);
	}
