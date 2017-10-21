    public static void remove(Element elem, boolean recurse) {
        elem.removeAttributeNS(URI, SRC_ATTR);
        elem.removeAttributeNS(URI, LINE_ATTR);
        elem.removeAttributeNS(URI, COL_ATTR);
        if (recurse) {
            NodeList children = elem.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    remove((Element)child, recurse);
                }
            }
        }
    }
