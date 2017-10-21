	private CandidateComponentsMetadata readGeneratedMetadata(File outputLocation) {
		try {
			File metadataFile = new File(outputLocation,
					MetadataStore.METADATA_PATH);
			if (metadataFile.isFile()) {
				return PropertiesMarshaller.read(new FileInputStream(metadataFile));
			}
			else {
				return new CandidateComponentsMetadata();
			}
		}
		catch (IOException ex) {
			throw new IllegalStateException("Failed to read metadata from disk", ex);
		}
	}
