		private MultipartHttpMessageWriter getMultipartHttpMessageWriter() {
			List<HttpMessageWriter<?>> partWriters;
			if (this.multipartCodecs != null) {
				partWriters = this.multipartCodecs.getWriters();
			}
			else {
				DefaultCustomCodecs customCodecs = getCustomCodecs();
				partWriters = new ArrayList<>();
				partWriters.addAll(super.getTypedWriters());
				if (customCodecs != null) {
					partWriters.addAll(customCodecs.getTypedWriters());
				}
				partWriters.addAll(super.getObjectWriters());
				if (customCodecs != null) {
					partWriters.addAll(customCodecs.getObjectWriters());
				}
				partWriters.addAll(super.getCatchAllWriters());
			}
			return new MultipartHttpMessageWriter(partWriters);
		}
