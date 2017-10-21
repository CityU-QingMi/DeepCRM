	protected Object unmarshalStaxSource(Unmarshaller jaxbUnmarshaller, Source staxSource) throws JAXBException {
		XMLStreamReader streamReader = StaxUtils.getXMLStreamReader(staxSource);
		if (streamReader != null) {
			return (this.mappedClass != null ?
					jaxbUnmarshaller.unmarshal(streamReader, this.mappedClass).getValue() :
					jaxbUnmarshaller.unmarshal(streamReader));
		}
		else {
			XMLEventReader eventReader = StaxUtils.getXMLEventReader(staxSource);
			if (eventReader != null) {
				return (this.mappedClass != null ?
						jaxbUnmarshaller.unmarshal(eventReader, this.mappedClass).getValue() :
						jaxbUnmarshaller.unmarshal(eventReader));
			}
			else {
				throw new IllegalArgumentException("StaxSource contains neither XMLStreamReader nor XMLEventReader");
			}
		}
	}
