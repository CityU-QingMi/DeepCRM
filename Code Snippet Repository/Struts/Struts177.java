    public static String getContent(Element element) {
        StringBuilder paramValue = new StringBuilder();
        NodeList childNodes = element.getChildNodes();
        for (int j = 0; j < childNodes.getLength(); j++) {
            Node currentNode = childNodes.item(j);
            if (currentNode != null && currentNode.getNodeType() == Node.TEXT_NODE) {
                String val = currentNode.getNodeValue();
                if (val != null) {
                    paramValue.append(val.trim());
                }
            }
        }
        return paramValue.toString().trim();
    }
