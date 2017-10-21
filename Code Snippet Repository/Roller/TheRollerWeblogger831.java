    public static String getQueryString(Map<String, String> params) {
        
        if(params == null) {
            return null;
        }
        
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {

            if (queryString.length() == 0) {
                queryString.append("?");
            } else {
                queryString.append("&");
            }

            queryString.append(entry.getKey());
            queryString.append("=");
            queryString.append(entry.getValue());
        }

        return queryString.toString();
    }
