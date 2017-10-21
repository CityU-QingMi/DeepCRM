    private RollerMessages parseTrackbackResponse(String response, RollerMessages messages) 
            throws JDOMException, IOException {
        
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(
                new StringReader(StringEscapeUtils.unescapeHtml4(response)));
        Element root = doc.getRootElement();
        
        if ("response".equals(root.getName())) {
            int code = -99;
            try {
                code = Integer.parseInt(root.getChildText("error"));
            } catch (NumberFormatException ignoredByDesign) {}
            
            String message = root.getChildText("message");
            if (code != 0) {
                messages.addError("weblogEdit.trackbackFailure", Utilities.removeHTML(message));
            } else {
                messages.addMessage("weblogEdit.trackbackSuccess");
            }
        } else {
            messages.addError("weblogEdit.trackbackErrorParsing", Utilities.removeHTML(response));
        }
        
        return messages;
    }
