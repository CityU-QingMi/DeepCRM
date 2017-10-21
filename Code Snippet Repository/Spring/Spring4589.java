		@Override
		public void completed(Integer read, AsynchronousFileChannel channel) {
			if (read != -1) {
				this.position += read;
				this.byteBuffer.flip();
				boolean release = true;
				DataBuffer dataBuffer = this.dataBufferFactory.allocateBuffer(read);
				try {
					dataBuffer.write(this.byteBuffer);
					release = false;
					this.sink.next(dataBuffer);
				}
				finally {
					if (release) {
						release(dataBuffer);
					}
				}
				this.byteBuffer.clear();

				if (!this.sink.isCancelled()) {
					channel.read(this.byteBuffer, this.position, channel, this);
				}
			}
			else {
				this.sink.complete();
				closeChannel(channel);
			}
		}
