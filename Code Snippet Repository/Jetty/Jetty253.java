    private StringBuilder convertCookies(List<HttpCookie> cookies, StringBuilder builder)
    {
        for (int i = 0; i < cookies.size(); ++i)
        {
            if (builder == null)
                builder = new StringBuilder();
            if (builder.length() > 0)
                builder.append("; ");
            HttpCookie cookie = cookies.get(i);
            builder.append(cookie.getName()).append("=").append(cookie.getValue());
        }
        return builder;
    }
