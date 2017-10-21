    public ReferenceObject findSynonym(String name, String schemaName,
                                       int type) {

        Schema schema = (Schema) schemaMap.get(schemaName);

        if (schema == null) {
            return null;
        }

        ReferenceObject reference = schema.findReference(name, type);

        return reference;
    }
