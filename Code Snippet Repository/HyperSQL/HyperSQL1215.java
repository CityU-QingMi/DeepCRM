    public LineGroupReader(LineNumberReader reader, String[] sectionStarts) {

        this.sectionStarts        = sectionStarts;
        this.sectionContinuations = ValuePool.emptyStringArray;
        this.ignoredStarts        = ValuePool.emptyStringArray;
        this.reader               = reader;

        try {
            getSection();
        } catch (Exception e) {}
    }
