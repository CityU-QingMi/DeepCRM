    Expression replaceAliasInOrderBy(Session session, Expression[] columns,
                                     int length) {

        if (isSelfAggregate()) {
            return this;
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            nodes[i] = nodes[i].replaceAliasInOrderBy(session, columns,
                    length);
        }

        return this;
    }
