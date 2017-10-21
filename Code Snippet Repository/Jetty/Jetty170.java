    public static boolean setIfNotSetAlready(Property property)
    {
        if (System.getProperty(property.getName()) == null)
        {
            System.setProperty(property.getName(), (property.getValue() == null ? "" : property
                    .getValue()));
            TaskLog.log("Setting property '" + property.getName() + "' to value '"
                    + property.getValue() + "'");
            return true;
        }

        return false;
    }
