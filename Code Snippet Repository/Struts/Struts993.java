    private Node constructAdapterInstance(Class adapterClass, AdapterNode parent, String propertyName, Object propertyValue) {
        // Check to see if the class has a no-args constructor
        try {
            adapterClass.getConstructor(new Class []{});
        } catch (NoSuchMethodException e1) {
            throw new StrutsException("Adapter class: " + adapterClass + " does not have a no-args constructor.");
        }

        try {
            AdapterNode adapterNode = (AdapterNode) adapterClass.newInstance();
            adapterNode.setAdapterFactory(this);
            adapterNode.setParent(parent);
            adapterNode.setPropertyName(propertyName);
            adapterNode.setPropertyValue(propertyValue);

            return adapterNode;

        } catch (IllegalAccessException | InstantiationException e) {
            throw new StrutsException("Cannot adapt " + propertyValue + " (" + propertyName + ") :" + e.getMessage(), e);
        }
    }
