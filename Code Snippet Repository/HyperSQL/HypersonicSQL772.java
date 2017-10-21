    public NumberSequence findSequence(Session session, String name,
                                       String schemaName) {

        NumberSequence seq = getSequence(name,
                                         session.getSchemaName(schemaName),
                                         false);

        if (seq == null && schemaName == null) {
            schemaName = session.getSchemaName(schemaName);

            ReferenceObject ref = findSynonym(name, schemaName,
                                              SchemaObject.SEQUENCE);

            if (ref != null) {
                seq = getSequence(ref.target.name, ref.target.schema.name,
                                  false);
            }
        }

        return seq;
    }
