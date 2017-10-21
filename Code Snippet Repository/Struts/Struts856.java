    public String getURL() {
        // all this trickier with maps is to reduce the number of objects created
        Map<String, Object> fullParams = null;

        if (params != null) {
            fullParams = new HashMap<String, Object>();
        }

        if (page == null) {
            // No particular page requested, so go to "same page"
            // Add query params to parameters
            if (fullParams != null) {
                fullParams.putAll(request.getParameterMap());
            } else {
                fullParams = request.getParameterMap();
            }
        }

        // added parameters override, just like in URLTag
        if (params != null) {
            fullParams.putAll(params);
        }

        return urlHelper.buildUrl(page, request, response, fullParams);
    }
