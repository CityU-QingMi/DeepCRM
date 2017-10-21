    private static MultiMap<String> parseQueryString(String url)
    {
        MultiMap<String> res = new MultiMap<String>();
        int questionMarkIndex = url.indexOf('?');
        if (questionMarkIndex == -1)
        {
            return res;
        }
        int poundIndex = url.indexOf('#');
        if (poundIndex == -1)
        {
            poundIndex = url.length();
        }
        UrlEncoded.decodeUtf8To(url, questionMarkIndex+1,
                    poundIndex - questionMarkIndex - 1, res);
        return res;
    }
