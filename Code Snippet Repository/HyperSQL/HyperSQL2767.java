    protected IgnoreParsedSection(HsqlArrayList sectionLines, char aType) {

/**/
/**/
/**/

        // Inefficient to parse this into SQL when we aren't going to use
        // it as SQL.  Should probably just be removed to use the
        // super() constructor.
        super(sectionLines);

        type = aType;
    }
