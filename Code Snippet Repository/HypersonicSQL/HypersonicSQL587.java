    private boolean resolvesSchemaName(String name) {

        if (name == null) {
            return true;
        }

        if (variables != null) {
            return false;
        }

        if (tableAlias != null) {
            return false;
        }

        return name.equals(rangeTable.getSchemaName().name);
    }
