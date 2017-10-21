    @Override
    public void setLocale(Locale locale)
    {
        if (locale == null || isCommitted() || isIncluding())
            return;

        _locale = locale;
        _fields.put(HttpHeader.CONTENT_LANGUAGE, locale.toString().replace('_', '-'));

        if (_outputType != OutputType.NONE)
            return;

        if (_channel.getRequest().getContext() == null)
            return;

        String charset = _channel.getRequest().getContext().getContextHandler().getLocaleEncoding(locale);

        if (charset != null && charset.length() > 0 && __localeOverride.contains(_encodingFrom))
            setCharacterEncoding(charset,EncodingFrom.SET_LOCALE);
    }
