	@Override
	protected XMLEvent internalNextEvent() throws XMLStreamException {
		//If there is an iterator to read from reset was called, use the iterator
		//until it runs out of events.
		if (this.bufferReader != null) {
			final XMLEvent event = this.bufferReader.next();

			//If nothing left in the iterator, remove the reference and fall through to direct reading
			if (!this.bufferReader.hasNext()) {
				this.bufferReader = null;
			}

			return event;
		}

		//Get the next event from the underlying reader
		final XMLEvent event = this.getParent().nextEvent();

		//if buffering add the event
		if (this.eventLimit != 0) {
			this.eventBuffer.offer(event);

			//If limited buffer size and buffer is too big trim the buffer.
			if (this.eventLimit > 0 && this.eventBuffer.size() > this.eventLimit) {
				this.eventBuffer.poll();
			}
		}

		return event;
	}
