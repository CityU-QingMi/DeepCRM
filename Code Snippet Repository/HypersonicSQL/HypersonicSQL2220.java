    public PIFGenerator(int sequenceNum) {

        this();

        if (sequenceNum < 1) {

            // No need to localize.  Would be caught at dev-time.
            throw new IllegalArgumentException("Sequence numbers start at 1");
        }

        //typeFlag = 'g';
        name = System.getProperty("java.io.tmpdir") + "/GlobalHead." + fakePid
               + '.' + sequenceNum;
    }
