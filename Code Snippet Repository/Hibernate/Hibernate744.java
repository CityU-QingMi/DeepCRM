	public void mark(int eventLimit) {
		this.eventLimit = eventLimit;

		//Buffering no events now, clear the buffer and buffered reader
		if (this.eventLimit == 0) {
			this.eventBuffer.clear();
			this.bufferReader = null;
		}
		//Buffering limited set of events, lets trim the buffer if needed
		else if (this.eventLimit > 0) {
			//If there is an iterator check its current position and calculate the new iterator start position
			int iteratorIndex = 0;
			if (this.bufferReader != null) {
				final int nextIndex = this.bufferReader.nextIndex();
				iteratorIndex = Math.max( 0, nextIndex - ( this.eventBuffer.size() - this.eventLimit ) );
			}

			//Trim the buffer until it is not larger than the limit
			while (this.eventBuffer.size() > this.eventLimit) {
				this.eventBuffer.poll();
			}

			//If there is an iterator re-create it using the newly calculated index
			if (this.bufferReader != null) {
				this.bufferReader = this.eventBuffer.listIterator(iteratorIndex);
			}
		}
	}
