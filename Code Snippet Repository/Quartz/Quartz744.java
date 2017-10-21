    public void testVersionParsing() {
        assertNonNegativeInteger(QuartzScheduler.getVersionMajor());
        assertNonNegativeInteger(QuartzScheduler.getVersionMinor());

        String iter = QuartzScheduler.getVersionIteration();
        assertNotNull(iter);
        Pattern suffix = Pattern.compile("(\\d+)(-\\w+)?");
        Matcher m = suffix.matcher(iter);
        if (m.matches()) {
          assertNonNegativeInteger(m.group(1));
        } else {
          throw new RuntimeException(iter + " doesn't match pattern '(\\d+)(-\\w+)?'");
        } 

    }
