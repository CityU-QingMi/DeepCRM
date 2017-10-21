    public static boolean isSystemSchemaName(String name) {

        if (SqlInvariants.DEFINITION_SCHEMA.equals(name)
                || SqlInvariants.INFORMATION_SCHEMA.equals(name)
                || SqlInvariants.SYSTEM_SCHEMA.equals(name)
                || SqlInvariants.SQLJ_SCHEMA.equals(name)) {
            return true;
        }

        return false;
    }
