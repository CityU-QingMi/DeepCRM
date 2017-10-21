    private String extractQueryString(UrlProvider urlComponent) {
        // Parse the query string to make sure that the parameters come from the query, and not some posted data
        String query = urlComponent.getHttpServletRequest().getQueryString();
        if (query == null) {
            query = (String) urlComponent.getHttpServletRequest().getAttribute("javax.servlet.forward.query_string");
        }

        if (query != null) {
            // Remove possible #foobar suffix
            int idx = query.lastIndexOf('#');

            if (idx != -1) {
                query = query.substring(0, idx);
            }
        }
        return query;
    }
