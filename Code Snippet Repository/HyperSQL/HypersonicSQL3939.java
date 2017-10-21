    private Directive getDirective(int column) {

        for (int i = 0; i < sortingColumns.size(); i++) {
            Directive directive = (Directive) sortingColumns.get(i);

            if (directive.column == column) {
                return directive;
            }
        }

        return EMPTY_DIRECTIVE;
    }
