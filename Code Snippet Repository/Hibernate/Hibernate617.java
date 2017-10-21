		@Override
		public XMLEvent peek() throws XMLStreamException {
			XMLEvent event = super.peek();
			if ( event.isStartElement() ) {
				return withNamespace( event.asStartElement() );
			}
			else {
				return event;
			}
		}
