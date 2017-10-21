    public void addSchemaObject(HsqlNameManager nameManager,
                                SchemaObject object, boolean replace) {

        HsqlName        name = object.getName();
        SchemaObjectSet set  = this.getObjectSet(name.type);

        switch (name.type) {

            case SchemaObject.PROCEDURE :
            case SchemaObject.FUNCTION : {
                RoutineSchema routine =
                    (RoutineSchema) set.getObject(name.name);

                if (routine == null) {
                    routine = new RoutineSchema(name.type, name);

                    routine.addSpecificRoutine(nameManager, (Routine) object,
                                               replace);
                    set.checkAdd(name);

                    SchemaObjectSet specificSet =
                        getObjectSet(SchemaObject.SPECIFIC_ROUTINE);

                    specificSet.checkAdd(((Routine) object).getSpecificName());
                    set.add(routine, replace);
                    specificSet.add(object, replace);
                } else {
                    SchemaObjectSet specificSet =
                        getObjectSet(SchemaObject.SPECIFIC_ROUTINE);
                    HsqlName specificName =
                        ((Routine) object).getSpecificName();

                    if (specificName != null) {
                        specificSet.checkAdd(specificName);
                    }

                    routine.addSpecificRoutine(nameManager, (Routine) object,
                                               replace);
                    specificSet.add(object, replace);
                }

                return;
            }
        }

        set.add(object, replace);
    }
