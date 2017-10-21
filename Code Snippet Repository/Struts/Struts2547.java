    public static void handle(String location, boolean flush) throws Exception {
        final HttpServletResponse response = ServletActionContext.getResponse();
        final HttpServletRequest request = ServletActionContext.getRequest();
        final UrlHelper urlHelper = ServletActionContext.getContext().getInstance(UrlHelper.class);

        int i = location.indexOf("?");
        if (i > 0) {
            //extract params from the url and add them to the request
            String query = location.substring(i + 1);
            Map<String, Object> queryParams = urlHelper.parseQueryString(query, true);
            if (queryParams != null && !queryParams.isEmpty()) {
                Map<String, Parameter> newParams = new HashMap<>();
                for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                    newParams.put(entry.getKey(), new Parameter.Request(entry.getKey(), entry.getValue()));
                }
                ActionContext.getContext().getParameters().appendAll(newParams);
            }
            location = location.substring(0, i);
        }

        Servlet servlet = servletCache.get(location);
        HttpJspPage page = (HttpJspPage) servlet;

        page._jspService(request, response);
        if (flush)
            response.flushBuffer();
    }
