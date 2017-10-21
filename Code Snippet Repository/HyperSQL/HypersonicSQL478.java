    HsqlName createLabelIfNull(StatementCompound context, HsqlName label) {

        if (label != null) {
            return label;
        }

        String            labelString;
        StatementCompound parent = context;
        int               level  = 0;

        while (parent != null) {
            level++;

            parent = parent.parent;
        }

        labelString = "_" + level;
        label = session.database.nameManager.newHsqlName(labelString, false,
                SchemaObject.LABEL);

        return label;
    }
