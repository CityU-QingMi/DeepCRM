        public void characters(char[] ch, int start,
                               int length) throws SAXException {

            checkClosed();

            Node   node = getCurrentNode().getLastChild();
            String s    = new String(ch, start, length);

            if ((node != null) && (node.getNodeType() == Node.TEXT_NODE)) {
                ((Text) node).appendData(s);
            } else {
                Text text = getDocument().createTextNode(s);

                getCurrentNode().appendChild(text);
            }
        }
