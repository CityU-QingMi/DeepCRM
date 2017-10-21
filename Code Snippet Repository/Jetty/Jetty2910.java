    @Override
    public Enumeration<Locale> getLocales()
    {
        MetaData.Request metadata = _metaData;
        if (metadata==null)
            return Collections.enumeration(__defaultLocale);

        List<String> acceptable = metadata.getFields().getQualityCSV(HttpHeader.ACCEPT_LANGUAGE);

        // handle no locale
        if (acceptable.isEmpty())
            return Collections.enumeration(__defaultLocale);

        List<Locale> locales = acceptable.stream().map(language->
        {
            language = HttpFields.stripParameters(language);
            String country = "";
            int dash = language.indexOf('-');
            if (dash > -1)
            {
                country = language.substring(dash + 1).trim();
                language = language.substring(0,dash).trim();
            }
            return new Locale(language,country);
        }).collect(Collectors.toList());
        
        return Collections.enumeration(locales);
    }
