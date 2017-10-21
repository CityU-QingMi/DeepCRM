	public BufferedImageHttpMessageConverter() {
		String[] readerMediaTypes = ImageIO.getReaderMIMETypes();
		for (String mediaType : readerMediaTypes) {
			if (StringUtils.hasText(mediaType)) {
				this.readableMediaTypes.add(MediaType.parseMediaType(mediaType));
			}
		}

		String[] writerMediaTypes = ImageIO.getWriterMIMETypes();
		for (String mediaType : writerMediaTypes) {
			if (StringUtils.hasText(mediaType)) {
				this.defaultContentType = MediaType.parseMediaType(mediaType);
				break;
			}
		}
	}
