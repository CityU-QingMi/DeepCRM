    protected Object resolveSchemaSource() {
        InputSource inputSource;

        InputStream is = null;

        try {
            is = classLoadHelper.getResourceAsStream(QUARTZ_XSD_PATH_IN_JAR);
        }  finally {
            if (is != null) {
                inputSource = new InputSource(is);
                inputSource.setSystemId(QUARTZ_SCHEMA_WEB_URL);
                log.debug("Utilizing schema packaged in local quartz distribution jar.");
            }
            else {
                log.info("Unable to load local schema packaged in quartz distribution jar. Utilizing schema online at " + QUARTZ_SCHEMA_WEB_URL);
                return QUARTZ_SCHEMA_WEB_URL;
            }
                
        }

        return inputSource;
    }
