    private String getErrorResponse(String message) {
        
        StringBuilder output = new StringBuilder();
        
        output.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
        output.append("<response>");
        output.append("<error>1</error>");
        output.append("<message>ERROR: ");
        output.append(message);
        output.append("</message>");
        output.append("</response>");
            
        return output.toString();
    }
