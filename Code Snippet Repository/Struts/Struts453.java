    public UrlSet matching(String pattern) {
        Map<String, URL> urls = new HashMap<>();
        for (Map.Entry<String, URL> entry : this.urls.entrySet()) {
            String url = entry.getKey();
            if (url.matches(pattern)){
                urls.put(url, entry.getValue());
            }
        }
        return new UrlSet(urls);
    }
