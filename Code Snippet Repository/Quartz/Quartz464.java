    private ByteArrayOutputStream serializeProperties(JobDataMap data)
        throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        if (null != data) {
            Properties properties = convertToProperty(data.getWrappedMap());
            properties.store(ba, "");
        }

        return ba;
    }
