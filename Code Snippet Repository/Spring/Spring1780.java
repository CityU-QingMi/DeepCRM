	private void writeMetaData() {
		CandidateComponentsMetadata metadata = this.metadataCollector.getMetadata();
		if (!metadata.getItems().isEmpty()) {
			try {
				this.metadataStore.writeMetadata(metadata);
			}
			catch (IOException ex) {
				throw new IllegalStateException("Failed to write metadata", ex);
			}
		}
	}
