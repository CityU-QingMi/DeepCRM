    public void add(SchemaObject object, boolean replace) {

        HsqlName name = object.getName();

        if (type == SchemaObject.SPECIFIC_ROUTINE) {
            name = ((Routine) object).getSpecificName();
        }

        if (!replace && map.containsKey(name.name)) {
            int code = getAddErrorCode(name.type);

            throw Error.error(code, name.name);
        }

        Object value = object;

        switch (name.type) {

            case SchemaObject.COLUMN :
            case SchemaObject.CONSTRAINT :
            case SchemaObject.INDEX :
                value = name;
                break;

            default :
        }

        map.put(name.name, value);
    }
