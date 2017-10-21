		@Override
		protected void releaseData() {
			if (logger.isTraceEnabled()) {
				logger.trace("releaseData: " + this.currentData);
			}
			DataBufferUtils.release(this.currentData);
			this.currentData = null;

			this.byteBuffer = null;
		}
