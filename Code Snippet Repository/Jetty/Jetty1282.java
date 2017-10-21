    public HttpCookie(String name, String value, String domain, String path, long maxAge, boolean httpOnly, boolean secure, String comment, int version)
    {
        _name = name;
        _value = value;
        _domain = domain;
        _path = path;
        _maxAge = maxAge;
        _httpOnly = httpOnly;
        _secure = secure;
        _comment = comment;
        _version = version;
        _expiration = maxAge < 0 ? -1 : System.nanoTime() + TimeUnit.SECONDS.toNanos(maxAge);
    }
