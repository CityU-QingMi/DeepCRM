		private DataBuffer generateHeaders() {
			DataBuffer buffer = this.bufferFactory.allocateBuffer();
			for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
				byte[] headerName = entry.getKey().getBytes(this.charset);
				for (String headerValueString : entry.getValue()) {
					byte[] headerValue = headerValueString.getBytes(this.charset);
					buffer.write(headerName);
					buffer.write((byte)':');
					buffer.write((byte)' ');
					buffer.write(headerValue);
					buffer.write((byte)'\r');
					buffer.write((byte)'\n');
				}
			}
			buffer.write((byte)'\r');
			buffer.write((byte)'\n');
			return buffer;
		}
