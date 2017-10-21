		@Override
		public Publisher<? extends XMLEvent> apply(DataBuffer dataBuffer) {
			try {
				streamReader.getInputFeeder().feedInput(dataBuffer.asByteBuffer());
				List<XMLEvent> events = new ArrayList<>();
				while (true) {
					if (streamReader.next() == AsyncXMLStreamReader.EVENT_INCOMPLETE) {
						// no more events with what currently has been fed to the reader
						break;
					}
					else {
						XMLEvent event = eventAllocator.allocate(streamReader);
						events.add(event);
						if (event.isEndDocument()) {
							break;
						}
					}
				}
				return Flux.fromIterable(events);
			}
			catch (XMLStreamException ex) {
				return Mono.error(ex);
			}
			finally {
				DataBufferUtils.release(dataBuffer);
			}
		}
