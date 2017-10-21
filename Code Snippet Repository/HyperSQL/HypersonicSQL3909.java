    public Class getColumnClass(int i) {

        if (rows.size() > 0) {
            Object o = getValueAt(0, i);

            if (o != null) {
                if ((o instanceof java.sql.Timestamp)
                    || (o instanceof java.sql.Time)) {
                    // This is a workaround for JTable's lack of a default
                    // renderer that displays times.
                    // Without this workaround, Timestamps (and similar
                    // classes) will be displayed as dates without times,
                    // since JTable will match these classes to their
                    // java.util.Date superclass.
                    return Object.class;  // renderer will draw .toString().
                }
                return o.getClass();
            }
        }

        return super.getColumnClass(i);
    }
