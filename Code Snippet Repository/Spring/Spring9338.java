	public void setDefaultContentType(@Nullable MediaType defaultContentType) {
		if (defaultContentType!= null) {
			Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByMIMEType(defaultContentType.toString());
			if (!imageWriters.hasNext()) {
				throw new IllegalArgumentException(
						"Content-Type [" + defaultContentType + "] is not supported by the Java Image I/O API");
			}
		}

		this.defaultContentType = defaultContentType;
	}
