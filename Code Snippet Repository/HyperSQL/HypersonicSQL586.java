    private boolean resolvesTableName(String name) {

        if (name == null) {
            return true;
        }

        if (variables != null) {
            if (tableAlias != null) {
                return name.equals(tableAlias.name);
            }

            return false;
        }

        if (tableAlias == null) {
            if (name.equals(rangeTable.getName().name)) {
                return true;
            }
        } else if (name.equals(tableAlias.name)) {
            return true;
        }

        return false;
    }
