    protected Icon getHeaderRendererIcon(int column, int size) {

        Directive directive = getDirective(column);

        if (directive == EMPTY_DIRECTIVE) {
            return null;
        }

        return new Arrow(directive.direction == DESCENDING, size,
                         sortingColumns.indexOf(directive));
    }
