    private String asXml() {
        final StringBuilder sb = new StringBuilder();
        final StructuredDataId sdId = getId();
        if (sdId == null || sdId.getName() == null || type == null) {
            return sb.toString();
        }
        sb.append("<StructuredData>\n");
        sb.append("<type>").append(type).append("</type>\n");
        sb.append("<id>").append(sdId).append("</id>\n");
        super.asXml(sb);
        sb.append("</StructuredData>\n");
        return sb.toString();
    }
