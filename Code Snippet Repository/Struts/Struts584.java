    public boolean end(Writer writer, String body) {
        String page = findString(value, "value", "You must specify the URL to include. Example: /foo.jsp");
        StringBuilder urlBuf = new StringBuilder();

        // Add URL
        urlBuf.append(page);

        // Add request parameters
        if (parameters.size() > 0) {
            urlBuf.append('?');

            String concat = "";

            // Set parameters
            for (Object next : parameters.entrySet()) {
                Map.Entry entry = (Map.Entry) next;
                Object name = entry.getKey();
                List values = (List) entry.getValue();

                for (Object value : values) {
                    urlBuf.append(concat);
                    urlBuf.append(name);
                    urlBuf.append('=');

                    try {
                        urlBuf.append(URLEncoder.encode(value.toString(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        LOG.warn("Unable to url-encode {}, it will be ignored", value);
                    }

                    concat = "&";
                }
            }
        }

        String result = urlBuf.toString();

        // Include
        try {
            include(result, writer, req, res, defaultEncoding);
        } catch (ServletException | IOException e) {
            LOG.warn("Exception thrown during include of {}", result, e);
        }

        return super.end(writer, body);
    }
