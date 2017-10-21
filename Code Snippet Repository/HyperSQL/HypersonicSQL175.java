    public Object getValue(Session session) {

        if (opType != OpTypes.LIKE) {
            return super.getValue(session);
        }

        Object leftValue   = nodes[LEFT].getValue(session);
        Object rightValue  = nodes[RIGHT].getValue(session);
        Object escapeValue = nodes[ESCAPE] == null ? null
                                                   : nodes[ESCAPE].getValue(
                                                       session);

        if (likeObject.isVariable) {
            synchronized (likeObject) {
                likeObject.setPattern(session, rightValue, escapeValue,
                                      nodes[ESCAPE] != null);

                return likeObject.compare(session, leftValue);
            }
        }

        return likeObject.compare(session, leftValue);
    }
