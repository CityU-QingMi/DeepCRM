    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            List<PropertyDescriptor> descriptors = new ArrayList<>();

            // Add the tricky one first
            Method classSetter = AbstractUITag.class.getMethod("setCssClass", String.class);
            Method styleSetter = AbstractUITag.class.getMethod("setCssStyle", String.class);

            descriptors.add(new PropertyDescriptor("class", null, classSetter));
            descriptors.add(new PropertyDescriptor("cssClass", null, classSetter));

            descriptors.add(new PropertyDescriptor("style", null, styleSetter));
            descriptors.add(new PropertyDescriptor("cssStyle", null, styleSetter));

            for (Field field : AbstractUITag.class.getDeclaredFields()) {
                String fieldName = field.getName();
                if (!"dynamicAttributes".equals(fieldName)) {
                    String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method setter = AbstractUITag.class.getMethod(setterName, String.class);
                    descriptors.add(new PropertyDescriptor(fieldName, null, setter));
                }
            }

            PropertyDescriptor[] array = new PropertyDescriptor[descriptors.size()];
            return descriptors.toArray(array);
        } catch (Exception e) {
            // This is crazy talk, we're only doing things that should always succeed
            LOG.fatal("Could not construct bean info for AbstractUITag. This is very bad.", e);
            return null;
        }
    }
