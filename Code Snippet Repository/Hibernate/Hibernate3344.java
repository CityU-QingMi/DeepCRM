	public static String getEventName(int eventId) {
		switch (eventId) {
			case XMLStreamConstants.START_ELEMENT:
				return "StartElementEvent";
			case XMLStreamConstants.END_ELEMENT:
				return "EndElementEvent";
			case XMLStreamConstants.PROCESSING_INSTRUCTION:
				return "ProcessingInstructionEvent";
			case XMLStreamConstants.CHARACTERS:
				return "CharacterEvent";
			case XMLStreamConstants.COMMENT:
				return "CommentEvent";
			case XMLStreamConstants.START_DOCUMENT:
				return "StartDocumentEvent";
			case XMLStreamConstants.END_DOCUMENT:
				return "EndDocumentEvent";
			case XMLStreamConstants.ENTITY_REFERENCE:
				return "EntityReferenceEvent";
			case XMLStreamConstants.ATTRIBUTE:
				return "AttributeBase";
			case XMLStreamConstants.DTD:
				return "DTDEvent";
			case XMLStreamConstants.CDATA:
				return "CDATA";
		}
		return "UNKNOWN_EVENT_TYPE";
	}
