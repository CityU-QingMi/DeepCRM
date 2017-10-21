	@Override
	@Nullable
	public String getMimeType(String filePath) {
		String extension = StringUtils.getFilenameExtension(filePath);
		if (this.mimeTypes.containsKey(extension)) {
			return this.mimeTypes.get(extension).toString();
		}
		else {
			return MediaTypeFactory.getMediaType(filePath).
					map(MimeType::toString)
					.orElse(null);
		}
	}
