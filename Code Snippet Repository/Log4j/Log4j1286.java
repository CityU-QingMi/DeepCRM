    @Override
    public byte[] getHeader() {
        if (!complete) {
            return null;
        }
        final StringBuilder buf = new StringBuilder();
        buf.append("<?xml version=\"1.0\" encoding=\"");
        buf.append(this.getCharset().name());
        buf.append("\"?>");
        buf.append(this.eol);
        // Make the log4j namespace the default namespace, no need to use more space with a namespace prefix.
        buf.append('<');
        buf.append(ROOT_TAG);
        buf.append(" xmlns=\"" + XmlConstants.XML_NAMESPACE + "\">");
        buf.append(this.eol);
        return buf.toString().getBytes(this.getCharset());
    }
