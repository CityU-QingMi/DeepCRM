    ReferenceObject findReference(String name, int type) {

        ReferenceObject ref = (ReferenceObject) referenceList.get(name);

        if (ref == null) {
            return null;
        }

        if (ref.getTarget().type == type) {
            return ref;
        }

        switch (type) {

            case SchemaObject.TABLE :
                if (ref.getTarget().type == SchemaObject.VIEW) {
                    return ref;
                }
            case SchemaObject.ROUTINE :
                if (ref.getTarget().type == SchemaObject.FUNCTION
                        || ref.getTarget().type == SchemaObject.PROCEDURE) {
                    return ref;
                }
        }

        return null;
    }
