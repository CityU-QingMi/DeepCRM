    private boolean findLabel(StatementSimple statement) {

        if (label != null && statement.label.name.equals(label.name)) {
            if (!isLoop && statement.getType() == StatementTypes.ITERATE) {
                return false;
            }

            return true;
        }

        if (parent == null) {
            return false;
        }

        return parent.findLabel(statement);
    }
