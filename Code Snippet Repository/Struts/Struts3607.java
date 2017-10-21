    private void handleDynamicMethodInvocation(ActionMapping mapping, String name) {
        int exclamation = name.lastIndexOf("!");
        if (exclamation != -1) {
            String actionName = name.substring(0, exclamation);
            String actionMethod = name.substring(exclamation + 1);

            // WW-4585
            // add any ; appendix to name, it will be handled later in getMapping method
            int scPos = actionMethod.indexOf(';');
            if (scPos != -1) {
                actionName = actionName + actionMethod.substring(scPos);
                actionMethod = actionMethod.substring(0, scPos);
            }

            mapping.setName(actionName);
            if (allowDynamicMethodCalls) {
                mapping.setMethod(cleanupMethodName(actionMethod));
            } else {
                mapping.setMethod(null);
            }
        }
    }
