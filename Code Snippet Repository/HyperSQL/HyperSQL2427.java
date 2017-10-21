    String getHead(String responseCodeString, boolean addInfo,
                   String mimeType, int length) {

        StringBuffer sb = new StringBuffer(128);

        sb.append(responseCodeString).append("\r\n");

        if (addInfo) {
            sb.append("Allow: GET, HEAD, POST\nMIME-Version: 1.0\r\n");
            sb.append("Server: ").append(
                HsqlDatabaseProperties.PRODUCT_NAME).append("\r\n");
        }

        if (mimeType != null) {
            sb.append("Cache-Control: no-cache\r\n");    // DB-traffic should not be cached by proxy's
            sb.append("Content-Type: ").append(mimeType).append("\r\n");

            //sb.append("Content-Length: ").append(length).append("\r\n");
        }

        sb.append("\r\n");

        return sb.toString();
    }
