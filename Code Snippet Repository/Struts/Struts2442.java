    protected Set<Binding> addBindings(Container container, Class type, String constName) {
        Set<Binding> bindings = new TreeSet<Binding>();
        String chosenName = container.getInstance(String.class, constName);
        if (chosenName == null) {
            chosenName = "struts";
        }
        Set<String> names = container.getInstanceNames(type);
        if (!names.contains(chosenName)) {
            bindings.add(new Binding(getInstanceClassName(container, type, "default"), chosenName, constName, true));
        }
        for (String name : names) {
            if (!"default".equals(name)) {
                bindings.add(new Binding(getInstanceClassName(container, type, name), name, constName, name.equals(chosenName)));
            }
        }
        return bindings;
    }
