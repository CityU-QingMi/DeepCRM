		@Override
		public void completed(Integer written, ByteBuffer byteBuffer) {
			this.position.addAndGet(written);
			if (byteBuffer.hasRemaining()) {
				this.channel.write(byteBuffer, this.position.get(), byteBuffer, this);
				return;
			}

			if (this.dataBuffer != null) {
				this.sink.next(this.dataBuffer);
				this.dataBuffer = null;
			}
			if (this.completed.get()) {
				this.sink.complete();
			}
			else {
				request(1);
			}
		}
