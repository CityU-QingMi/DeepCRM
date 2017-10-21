    public Collation(HsqlName name, Collation source, Charset charset,
                     Boolean padSpace) {

        this.name            = name;
        this.locale          = source.locale;
        this.collator        = source.collator;
        this.isUnicodeSimple = source.isUnicodeSimple;
        this.isFinal         = true;

        //
        this.charset    = charset;
        this.sourceName = source.name;

        if (padSpace != null) {
            this.padSpace = padSpace.booleanValue();
        }
    }
