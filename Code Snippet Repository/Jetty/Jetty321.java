    @Override
    public Request accept(String... accepts)
    {
        StringBuilder result = new StringBuilder();
        for (String accept : accepts)
        {
            if (result.length() > 0)
                result.append(", ");
            result.append(accept);
        }
        if (result.length() > 0)
            headers.put(HttpHeader.ACCEPT, result.toString());
        return this;
    }
