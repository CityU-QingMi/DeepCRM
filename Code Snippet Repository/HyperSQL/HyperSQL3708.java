    public boolean hasCoreTriggeredAction() {

        switch (core.deleteAction) {

            case SchemaObject.ReferentialAction.CASCADE :
            case SchemaObject.ReferentialAction.SET_DEFAULT :
            case SchemaObject.ReferentialAction.SET_NULL :
                return true;
        }

        switch (core.updateAction) {

            case SchemaObject.ReferentialAction.CASCADE :
            case SchemaObject.ReferentialAction.SET_DEFAULT :
            case SchemaObject.ReferentialAction.SET_NULL :
                return true;
        }

        return false;
    }
