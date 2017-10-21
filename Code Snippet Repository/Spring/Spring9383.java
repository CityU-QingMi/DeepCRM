		@Override
		public void merge(InputStream input, Charset charset, MediaType contentType,
				ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {

			if (contentType.isCompatibleWith(APPLICATION_JSON)) {
				this.jsonFormatter.merge(input, charset, extensionRegistry, builder);
			}
			else if (contentType.isCompatibleWith(APPLICATION_XML)) {
				this.xmlFormatter.merge(input, charset, extensionRegistry, builder);
			}
			else {
				throw new IOException("com.google.protobuf.util does not support " + contentType + " format");
			}
		}
