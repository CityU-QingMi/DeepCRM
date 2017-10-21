		@Override
		public void stop() {
			for ( InputStreamWrapper openedStream : openedStreams ) {
				if ( !openedStream.wasClosed ) {
					try {
						openedStream.close();
					}
					catch (IOException ignore) {
					}
				}
			}
			openedStreams.clear();
			stopped = true;
			super.stop();
		}
