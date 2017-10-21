	private Document toDom4jDocument(XMLEventReader jpaOrmXmlEventReader, Origin origin) {
		// todo : do we need to build a DocumentFactory instance for use here?
		//		historically we did that to set TCCL since, iirc, dom4j uses TCCL
		org.dom4j.io.STAXEventReader staxToDom4jReader = new STAXEventReader() {
			@Override
			public Node readNode(XMLEventReader reader) throws XMLStreamException {
				// dom4j's reader misses handling of XML comments.  So if the document we
				// are trying to read has comments this process will blow up.  So we
				// override that to add that support as best we can
				XMLEvent event = reader.peek();
				if ( javax.xml.stream.events.Comment.class.isInstance( event ) ) {
					return super.readComment( reader );
				}
				return super.readNode( reader );
			}
		};
		try {
			return staxToDom4jReader.readDocument( jpaOrmXmlEventReader );
		}
		catch (XMLStreamException e) {
			throw new MappingException(
					"An error occurred transforming orm.xml document from StAX to dom4j representation ",
					e,
					origin
			);
		}
	}
