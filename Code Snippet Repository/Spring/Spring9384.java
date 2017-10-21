		@Override
		public void print(Message message, OutputStream output, MediaType contentType, Charset charset)
				throws IOException {

			if (contentType.isCompatibleWith(APPLICATION_JSON)) {
				this.jsonFormatter.print(message, output, charset);
			}
			else if (contentType.isCompatibleWith(APPLICATION_XML)) {
				this.xmlFormatter.print(message, output, charset);
			}
			else if (contentType.isCompatibleWith(TEXT_HTML)) {
				this.htmlFormatter.print(message, output, charset);
			}
			else {
				throw new IOException("protobuf-java-format does not support " + contentType + " format");
			}
		}
