	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		Node parent = getParent();
		Node lastChild = parent.getLastChild();
		if (lastChild != null && lastChild.getNodeType() == Node.TEXT_NODE) {
			((Text) lastChild).appendData(data);
		}
		else {
			Text text = document.createTextNode(data);
			parent.appendChild(text);
		}
	}
