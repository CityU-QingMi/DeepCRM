    @Override
    public String getFormat() {
        StringBuilder sb = new StringBuilder();
        for (StructuredDataMessage msg : structuredDataMessageList) {
            if (msg.getFormat() != null) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(msg.getFormat());
            }
        }
        return sb.toString();
    }
