    public static boolean isSchemaNameSystem(HsqlName name) {

        if (name.schema != null) {
            name = name.schema;
        }

        if (SqlInvariants.INFORMATION_SCHEMA_HSQLNAME.equals(name)
                || SqlInvariants.SYSTEM_SCHEMA_HSQLNAME.equals(name)
                || SqlInvariants.SQLJ_SCHEMA_HSQLNAME.equals(name)) {
            return true;
        }

        return false;
    }
