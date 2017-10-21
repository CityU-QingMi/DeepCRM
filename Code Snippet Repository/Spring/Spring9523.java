	public MappingMediaTypeFileExtensionResolver(@Nullable Map<String, MediaType> mediaTypes) {
		if (mediaTypes != null) {
			for (Entry<String, MediaType> entries : mediaTypes.entrySet()) {
				String extension = entries.getKey().toLowerCase(Locale.ENGLISH);
				MediaType mediaType = entries.getValue();
				this.mediaTypes.put(extension, mediaType);
				this.fileExtensions.add(mediaType, extension);
				this.allFileExtensions.add(extension);
			}
		}
	}
