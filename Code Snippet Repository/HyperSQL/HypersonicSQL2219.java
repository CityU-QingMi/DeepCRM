    protected PIFGenerator() {

        try {
            writer = new OutputStreamWriter(this, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(
                "Serious problem.  JVM can't encode UTF-8", uee);
        }

        fakePid = (int) (new Date().getTime() % 100000L);

        // Java doesn't have access to PIDs, as PIF wants in the "name" field,
        // so we emulate one in a way that is easy for us.
    }
