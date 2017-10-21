	private XMLEvent wrap(XMLEvent event) {
		if ( event != null ) {
			if ( event.isStartElement() ) {
				return wrap( event.asStartElement() );
			}
			else if ( event.isEndElement() ) {
				return wrap( event.asEndElement() );
			}
		}
		return event;
	}
