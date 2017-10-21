    @Override
    public Locale getLocale()
    {
        MetaData.Request metadata = _metaData;
        if (metadata==null)
            return Locale.getDefault();

        List<String> acceptable = metadata.getFields().getQualityCSV(HttpHeader.ACCEPT_LANGUAGE);

        // handle no locale
        if (acceptable.isEmpty())
            return Locale.getDefault();

        String language = acceptable.get(0);
        language = HttpFields.stripParameters(language);
        String country = "";
        int dash = language.indexOf('-');
        if (dash > -1)
        {
            country = language.substring(dash + 1).trim();
            language = language.substring(0,dash).trim();
        }
        return new Locale(language,country);        
    }
