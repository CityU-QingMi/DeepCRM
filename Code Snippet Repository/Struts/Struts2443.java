    String getInstanceClassName(Container container, Class type, String name) {
        String instName = "Class unable to be loaded";
        try {
            Object inst = container.getInstance(type, name);
            instName = inst.getClass().getName();
        } catch (Exception ex) {
            // Ignoring beans unable to be loaded
        }
        return instName;
    }
