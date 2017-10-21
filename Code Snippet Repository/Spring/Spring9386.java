		@Override
		public void print(Message message, OutputStream output, MediaType contentType, Charset charset)
				throws IOException {

			if (contentType.isCompatibleWith(APPLICATION_JSON)) {
				OutputStreamWriter writer = new OutputStreamWriter(output, charset);
				this.printer.appendTo(message, writer);
				writer.flush();
			}
			else {
				throw new IOException("protobuf-java-util does not support " + contentType + " format");
			}
		}
