    protected Component findAncestor(Class clazz) {
        Stack componentStack = getComponentStack();
        int currPosition = componentStack.search(this);
        if (currPosition >= 0) {
            int start = componentStack.size() - currPosition - 1;

            //for (int i = componentStack.size() - 2; i >= 0; i--) {
            for (int i = start; i >=0; i--) {
                Component component = (Component) componentStack.get(i);
                if (clazz.isAssignableFrom(component.getClass()) && component != this) {
                    return component;
                }
            }
        }

        return null;
    }
