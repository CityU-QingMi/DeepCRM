    void collectFKWriteLocks(int[] columnMap, OrderedHashSet set) {

        for (int i = 0; i < fkMainConstraints.length; i++) {
            Constraint constraint  = fkMainConstraints[i];
            Table      ref         = constraint.getRef();
            int[]      mainColumns = constraint.getMainColumns();

            if (ref == this) {
                continue;
            }

            if (columnMap == null) {
                if (constraint.core.hasDeleteAction) {
                    int[] cols =
                        constraint.getDeleteAction()
                        == SchemaObject.ReferentialAction.CASCADE ? null
                                                                  : constraint
                                                                      .getRefColumns();

                    if (set.add(ref.getName())) {
                        ref.collectFKWriteLocks(cols, set);
                    }
                }
            } else if (ArrayUtil.haveCommonElement(columnMap, mainColumns)) {
                if (constraint.core.hasUpdateAction) {
                    if (set.add(ref.getName())) {
                        ref.collectFKWriteLocks(constraint.getRefColumns(),
                                                set);
                    }
                }
            }
        }
    }
