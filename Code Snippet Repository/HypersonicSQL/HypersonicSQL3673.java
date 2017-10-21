    public String getPartString(Session session, Object dateTime, int part) {

        String javaPattern = "";

        switch (part) {

            case DAY_NAME :
                javaPattern = "EEEE";
                break;

            case MONTH_NAME :
                javaPattern = "MMMM";
                break;
        }

        SimpleDateFormat format = session.getSimpleDateFormatGMT();

        try {
            format.applyPattern(javaPattern);
        } catch (Exception e) {}

        Date date = (Date) convertSQLToJavaGMT(session, dateTime);

        return format.format(date);
    }
