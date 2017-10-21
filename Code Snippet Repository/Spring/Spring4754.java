		@Override
		public int read() {
			if (this.currentBuffer == null) {
				// This stream doesn't have any data in it...
				return -1;
			}
			else {
				if (this.nextIndexInCurrentBuffer < this.currentBufferLength) {
					this.totalBytesRead++;
					return this.currentBuffer[this.nextIndexInCurrentBuffer++];
				}
				else {
					if (this.buffersIterator.hasNext()) {
						this.currentBuffer = this.buffersIterator.next();
						updateCurrentBufferLength();
						this.nextIndexInCurrentBuffer = 0;
					}
					else {
						this.currentBuffer = null;
					}
					return read();
				}
			}
		}
