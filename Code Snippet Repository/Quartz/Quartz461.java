    protected ByteArrayOutputStream serializeJobData(JobDataMap data)
        throws IOException {
        if (canUseProperties()) {
            return serializeProperties(data);
        }

        try {
            return serializeObject(data);
        } catch (NotSerializableException e) {
            throw new NotSerializableException(
                "Unable to serialize JobDataMap for insertion into " + 
                "database because the value of property '" + 
                getKeyOfNonSerializableValue(data) + 
                "' is not serializable: " + e.getMessage());
        }
    }
