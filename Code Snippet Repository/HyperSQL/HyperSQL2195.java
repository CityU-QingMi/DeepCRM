    public RowInputText(TextFileSettings textFileSettings) {

        super(new byte[0]);

        scanner               = new Scanner();
        this.textFileSettings = textFileSettings;
        this.fieldSep         = textFileSettings.fs;
        this.varSep           = textFileSettings.vs;
        this.longvarSep       = textFileSettings.lvs;

        //-- Newline indicates that field should match to end of line.
        if (fieldSep.endsWith("\n")) {
            fieldSepEnd = true;
            fieldSep    = fieldSep.substring(0, fieldSep.length() - 1);
        }

        if (varSep.endsWith("\n")) {
            varSepEnd = true;
            varSep    = varSep.substring(0, varSep.length() - 1);
        }

        if (longvarSep.endsWith("\n")) {
            longvarSepEnd = true;
            longvarSep    = longvarSep.substring(0, longvarSep.length() - 1);
        }

        fieldSepLen   = fieldSep.length();
        varSepLen     = varSep.length();
        longvarSepLen = longvarSep.length();
    }
