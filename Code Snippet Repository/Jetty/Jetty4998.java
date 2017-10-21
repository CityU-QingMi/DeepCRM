    public RolloverFileOutputStream(String filename,
                                    boolean append,
                                    int retainDays,
                                    TimeZone zone,
                                    String dateFormat,
                                    String backupFormat)
        throws IOException
    {
        this(filename,append,retainDays,zone,dateFormat,backupFormat,ZonedDateTime.now(zone.toZoneId()));
    }
