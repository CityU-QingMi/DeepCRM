    public void setBufferValue(String value, Node n, ErrorDispatcher err)
        throws JasperException {

        if ("none".equalsIgnoreCase(value))
            buffer = 0;
        else {
            if (value == null || !value.endsWith("kb"))
                err.jspError(n, "jsp.error.page.invalid.buffer");
            try {
                Integer k = new Integer(value.substring(0, value.length()-2));
                buffer = k.intValue() * 1024;
            } catch (NumberFormatException e) {
                err.jspError(n, "jsp.error.page.invalid.buffer");
            }
        }

        bufferValue = value;
    }
