    HsqlName getName(String name) {

        switch (type) {

            case SchemaObject.VIEW :
            case SchemaObject.TABLE :
            case SchemaObject.SEQUENCE :
            case SchemaObject.CHARSET :
            case SchemaObject.DOMAIN :
            case SchemaObject.TYPE :
            case SchemaObject.COLLATION :
            case SchemaObject.PROCEDURE :
            case SchemaObject.SPECIFIC_ROUTINE :
            case SchemaObject.FUNCTION :
            case SchemaObject.ASSERTION :
            case SchemaObject.TRIGGER :
            case SchemaObject.REFERENCE :
                SchemaObject object = ((SchemaObject) map.get(name));

                return object == null ? null
                                      : object.getName();

            case SchemaObject.COLUMN :
            case SchemaObject.CONSTRAINT :
            case SchemaObject.INDEX : {
                return (HsqlName) map.get(name);
            }
            default :
                return (HsqlName) map.get(name);
        }
    }
