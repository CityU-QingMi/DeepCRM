    private String prepare(String value) {
    	String result = value;
        if (escapeHtml) {
        	result = StringEscapeUtils.escapeHtml4(result);
        }
        if (escapeJavaScript) {
        	result = StringEscapeUtils.escapeEcmaScript(result);
        }
        if (escapeXml) {
        	result = StringEscapeUtils.escapeXml(result);
        }
        if (escapeCsv) {
            result = StringEscapeUtils.escapeCsv(result);
        }

        return result;
    }
