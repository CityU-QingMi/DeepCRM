    private void readComment(String str) {
        int lastUpdatePos = str.indexOf(LAST_UPDATE_STR);
        if (lastUpdatePos > -1) {
            str = str.substring(lastUpdatePos + LAST_UPDATE_STR.length());
            str = str.trim();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                lastModified = DateUtil.parse(str, sdf);
            } catch (ParseException e) {
                mLogger.debug("ParseException reading " + str);
            }
        }
    }
