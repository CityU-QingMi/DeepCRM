    SchemaObject findAnySchemaObject(String name) {

        int[] types = {
            SchemaObject.SEQUENCE, SchemaObject.TABLE, SchemaObject.ROUTINE
        };

        for (int type : types) {
            SchemaObject object = findSchemaObject(name, type);

            if (object != null) {
                return object;
            }
        }

        return null;
    }
