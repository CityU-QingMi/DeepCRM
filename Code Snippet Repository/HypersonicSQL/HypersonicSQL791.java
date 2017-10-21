    public Iterator databaseObjectIterator(int type) {

        readLock.lock();

        try {
            Iterator it      = schemaMap.values().iterator();
            Iterator objects = new WrapperIterator();

            while (it.hasNext()) {
                int targetType = type;

                if (type == SchemaObject.ROUTINE) {
                    targetType = SchemaObject.FUNCTION;
                }

                Schema          temp = (Schema) it.next();
                SchemaObjectSet set  = temp.getObjectSet(targetType);
                Object[]        values;

                if (set.map.size() != 0) {
                    values = new Object[set.map.size()];

                    set.map.valuesToArray(values);

                    objects = new WrapperIterator(objects,
                                                  new WrapperIterator(values));
                }

                if (type == SchemaObject.ROUTINE) {
                    set = temp.getObjectSet(SchemaObject.PROCEDURE);

                    if (set.map.size() != 0) {
                        values = new Object[set.map.size()];

                        set.map.valuesToArray(values);

                        objects =
                            new WrapperIterator(objects,
                                                new WrapperIterator(values));
                    }
                }
            }

            return objects;
        } finally {
            readLock.unlock();
        }
    }
