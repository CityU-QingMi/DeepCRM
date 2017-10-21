    public String buildResourceUrl(String value, Map params) {
        StringBuffer sb = new StringBuffer();
        // Relative URLs are not allowed in a portlet
        if (!value.startsWith("/")) {
            sb.append("/");
        }
        sb.append(value);
        if(params != null && params.size() > 0) {
            sb.append("?");
            Iterator it = params.keySet().iterator();
            try {
            while(it.hasNext()) {
                String key = (String)it.next();
                String val = (String)params.get(key);

                sb.append(URLEncoder.encode(key, ENCODING)).append("=");
                sb.append(URLEncoder.encode(val, ENCODING));
                if(it.hasNext()) {
                    sb.append("&");
                }
            }
            } catch (UnsupportedEncodingException e) {
                throw new StrutsException("Encoding "+ENCODING+" not found");
            }
        }
        PortletRequest req = PortletActionContext.getRequest();
        return encodeUrl(sb, req);
    }
