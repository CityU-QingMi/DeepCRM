    public void checkSchemaNameCanChange(HsqlName name) {

        readLock.lock();

        try {
            Iterator it      = referenceMap.values().iterator();
            HsqlName refName = null;

            mainLoop:
            while (it.hasNext()) {
                refName = (HsqlName) it.next();

                switch (refName.type) {

                    case SchemaObject.VIEW :
                    case SchemaObject.ROUTINE :
                    case SchemaObject.FUNCTION :
                    case SchemaObject.PROCEDURE :
                    case SchemaObject.TRIGGER :
                    case SchemaObject.SPECIFIC_ROUTINE :
                        if (refName.schema == name) {
                            break mainLoop;
                        }
                        break;

                    default :
                }

                refName = null;
            }

            if (refName == null) {
                return;
            }

            throw Error.error(ErrorCode.X_42502,
                              refName.getSchemaQualifiedStatementName());
        } finally {
            readLock.unlock();
        }
    }
