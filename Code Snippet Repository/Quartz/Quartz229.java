    private Properties overrideWithSysProps(Properties props) {
        Properties sysProps = null;
        try {
            sysProps = System.getProperties();
        } catch (AccessControlException e) {
            getLog().warn(
                "Skipping overriding quartz properties with System properties " +
                "during initialization because of an AccessControlException.  " +
                "This is likely due to not having read/write access for " +
                "java.util.PropertyPermission as required by java.lang.System.getProperties().  " +
                "To resolve this warning, either add this permission to your policy file or " +
                "use a non-default version of initialize().",
                e);
        }

        if (sysProps != null) {
            props.putAll(sysProps);
        }

        return props;
    }
