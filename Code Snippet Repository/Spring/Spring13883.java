		public List<Message<byte[]>> decode(WebSocketMessage<?> webSocketMessage) {
			List<Message<byte[]>> result = Collections.emptyList();
			ByteBuffer byteBuffer;
			if (webSocketMessage instanceof TextMessage) {
				byteBuffer = ByteBuffer.wrap(((TextMessage) webSocketMessage).asBytes());
			}
			else if (webSocketMessage instanceof BinaryMessage) {
				byteBuffer = ((BinaryMessage) webSocketMessage).getPayload();
			}
			else {
				return result;
			}
			result = this.bufferingDecoder.decode(byteBuffer);
			if (result.isEmpty()) {
				if (logger.isTraceEnabled()) {
					logger.trace("Incomplete STOMP frame content received, bufferSize=" +
							this.bufferingDecoder.getBufferSize() + ", bufferSizeLimit=" +
							this.bufferingDecoder.getBufferSizeLimit() + ".");
				}
			}
			return result;
		}
