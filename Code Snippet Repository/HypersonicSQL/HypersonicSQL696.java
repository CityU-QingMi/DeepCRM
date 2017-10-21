    public synchronized String describe(Session session) {

        StringBuilder sb     = new StringBuilder();
        RowActionBase action = this;

        do {
            if (action == this) {
                sb.append(this.rowId).append(' ');
            }

            sb.append(action.session.getId()).append(' ');
            sb.append(action.type).append(' ').append(action.actionTimestamp);
            sb.append(' ').append(action.commitTimestamp);

            if (action.commitTimestamp != 0) {
                if (action.rolledback) {
                    sb.append('r');
                } else {
                    sb.append('c');
                }
            }

            sb.append(" - ");

            action = action.next;
        } while (action != null);

        return sb.toString();
    }
