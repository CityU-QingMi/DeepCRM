		@Override
		public ReadableByteChannel apply(ReadableByteChannel channel, SynchronousSink<DataBuffer> sub) {
			try {
				int read;
				if ((read = channel.read(this.byteBuffer)) >= 0) {
					this.byteBuffer.flip();
					boolean release = true;
					DataBuffer dataBuffer = this.dataBufferFactory.allocateBuffer(read);
					try {
						dataBuffer.write(this.byteBuffer);
						release = false;
						sub.next(dataBuffer);
					}
					finally {
						if (release) {
							release(dataBuffer);
						}
					}
					this.byteBuffer.clear();
				}
				else {
					sub.complete();
				}
			}
			catch (IOException ex) {
				sub.error(ex);
			}
			return channel;
		}
