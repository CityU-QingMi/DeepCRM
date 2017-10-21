        public int compareTo(Object o) {

            int row1 = modelIndex;
            int row2 = ((Row) o).modelIndex;

            for (Iterator it = sortingColumns.iterator(); it.hasNext(); ) {
                Directive directive  = (Directive) it.next();
                int       column     = directive.column;
                Object    o1         = tableModel.getValueAt(row1, column);
                Object    o2         = tableModel.getValueAt(row2, column);
                int       comparison = 0;

                // Define null less than everything, except null.
                if (o1 == null && o2 == null) {
                    comparison = 0;
                } else if (o1 == null) {
                    comparison = -1;
                } else if (o2 == null) {
                    comparison = 1;
                } else {
                    comparison = getComparator(column).compare(o1, o2);
                }

                if (comparison != 0) {
                    return directive.direction == DESCENDING ? -comparison
                                                             : comparison;
                }
            }

            return 0;
        }
