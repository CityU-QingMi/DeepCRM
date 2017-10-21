    public Map<String, RuntimeConfigProperty> getProperties() throws WebloggerException {

        HashMap<String, RuntimeConfigProperty> props = new HashMap<String, RuntimeConfigProperty>();
        List<RuntimeConfigProperty> list = strategy.getNamedQuery("RuntimeConfigProperty.getAll",
                RuntimeConfigProperty.class).getResultList();
/**/
/**/
/**/
/**/
/**/
/**/
        for (RuntimeConfigProperty prop : list) {
            props.put(prop.getName(), prop);
        }
        return props;
    }
