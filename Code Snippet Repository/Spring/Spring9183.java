	private ContentDisposition(@Nullable String type, @Nullable String name, @Nullable String filename,
			@Nullable Charset charset, @Nullable Long size, @Nullable ZonedDateTime creationDate,
			@Nullable ZonedDateTime modificationDate, @Nullable ZonedDateTime readDate) {

		this.type = type;
		this.name = name;
		this.filename = filename;
		this.charset = charset;
		this.size = size;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.readDate = readDate;
	}
