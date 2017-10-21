        public void startElement(String uri, String localName, String qName,
                                 Attributes atts) throws SAXException {

            checkClosed();

            Element element;

            if ((uri == null) || (uri.length() == 0)) {
                element = getDocument().createElement(qName);
            } else {
                element = getDocument().createElementNS(uri, qName);
            }

            if (atts != null) {
                for (int i = 0; i < atts.getLength(); i++) {
                    String attrURI   = atts.getURI(i);
                    String attrQName = atts.getQName(i);
                    String attrValue = atts.getValue(i);

                    if ((attrURI == null) || (attrURI.length() == 0)) {
                        element.setAttribute(attrQName, attrValue);
                    } else {
                        element.setAttributeNS(attrURI, attrQName, attrValue);
                    }
                }
            }
            getCurrentNode().appendChild(element);
            setCurrentNode(element);

            if (getCurrentElement() == null) {
                setCurrentElement(element);
            }
        }
