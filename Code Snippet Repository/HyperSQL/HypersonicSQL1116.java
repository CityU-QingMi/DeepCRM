    public TableBase duplicate() {

        TableBase copy;

        try {
            copy = (TableBase) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
        }

        copy.persistenceId = database.persistentStoreCollection.getNextId();

        return copy;
    }
