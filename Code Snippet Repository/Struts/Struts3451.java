    private List<Configurer> buildXMLConfigurers(Class clazz, String context, boolean checkFile, Set<String> checked) {
        List<Configurer> configurers = new ArrayList<Configurer>();

        if (checked == null) {
            checked = new TreeSet<String>();
        } else if (checked.contains(clazz.getName())) {
            return configurers;
        }

        if (clazz.isInterface()) {
            for (Class anInterface : clazz.getInterfaces()) {
                configurers.addAll(buildXMLConfigurers(anInterface, context, checkFile, checked));
            }
        } else {
            if (!clazz.equals(Object.class)) {
                configurers.addAll(buildXMLConfigurers(clazz.getSuperclass(), context, checkFile, checked));
            }
        }

        // look for validators for implemented interfaces
        for (Class anInterface1 : clazz.getInterfaces()) {
            if (checked.contains(anInterface1.getName())) {
                continue;
            }

            addIfNotNull(configurers, buildClassValidatorConfigs(anInterface1, checkFile));

            if (context != null) {
                addIfNotNull(configurers, buildAliasValidatorConfigs(anInterface1, context, checkFile));
            }

            checked.add(anInterface1.getName());
        }

        addIfNotNull(configurers, buildClassValidatorConfigs(clazz, checkFile));

        if (context != null) {
            addIfNotNull(configurers, buildAliasValidatorConfigs(clazz, context, checkFile));
        }

        checked.add(clazz.getName());

        return configurers;
    }
