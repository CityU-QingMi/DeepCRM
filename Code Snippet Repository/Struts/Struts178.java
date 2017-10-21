     public static Integer getLoadOrder(Document doc) {
        Element rootElement = doc.getDocumentElement();
        String number = rootElement.getAttribute("order");
        if (StringUtils.isNotBlank(number)) {
            try {
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
                return Integer.MAX_VALUE;
            }
        } else {
            //no order specified
            return Integer.MAX_VALUE;
        }
    }
