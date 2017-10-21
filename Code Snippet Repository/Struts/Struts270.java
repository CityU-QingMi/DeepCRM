        public void beforeResult(ActionInvocation invocation, String resultCode) {
            ValueStack stack = invocation.getStack();
            CompoundRoot root = stack.getRoot();

            boolean needsRefresh = true;
            Object newModel = action.getModel();

            // Check to see if the new model instance is already on the stack
            for (Object item : root) {
                if (item.equals(newModel)) {
                    needsRefresh = false;
                    break;
                }
            }

            // Add the new model on the stack
            if (needsRefresh) {

                // Clear off the old model instance
                if (originalModel != null) {
                    root.remove(originalModel);
                }
                if (newModel != null) {
                    stack.push(newModel);
                }
            }
        }
